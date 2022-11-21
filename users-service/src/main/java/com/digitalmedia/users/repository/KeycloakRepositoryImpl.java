package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class KeycloakRepositoryImpl implements IKeycloakRepository{

    private final Keycloak keycloakClient;
    @Value("${dh.keycloak.realm}")
    private String realm;

    @Autowired
    public KeycloakRepositoryImpl(Keycloak keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        List<UserRepresentation> userRepresentation = keycloakClient.realm(realm).users().search(username);
        System.out.println(userRepresentation);
        List<User> userResponseMap = userRepresentation
                .stream()
                .map(ur -> toUser(ur))
                .collect(Collectors.toList());
        return userResponseMap.stream().findFirst();
    }

    @Override
    public Optional<User> findById(String id) {
        UserRepresentation ur = keycloakClient
                .realm(realm)
                .users()
                .get(id)
                .toRepresentation();
        return Optional.of(toUser(ur));
    }

    @Override
    public List<User> getAllNoAdmin() {
        List<UserRepresentation> usersR = keycloakClient.realm(realm)
                .users()
                .list();
        log.info(String.format("user representation: %s ", usersR.size()));
        List<UserRepresentation> usersEnabled = usersR.stream().filter(
                userRepresentation -> {
                    List<GroupRepresentation> groups = keycloakClient.realm(realm)
                            .users()
                            .get(userRepresentation.getId())
                            .groups();
                    return groups.stream().noneMatch(s ->Objects.equals(
                            s.getName(), "admin"));
                }
        ).collect(Collectors.toList());

        return usersEnabled.stream()
                .map(u -> toUser(u))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<User> updateAvatar(String id, String avatar) {
        UserResource userResource = keycloakClient
                .realm(realm)
                .users()
                .get(id);
        UserRepresentation user = userResource.toRepresentation();

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("avatar", List.of(avatar));
        user.setAttributes(attributes);


        userResource.update(user);
        return Optional.of(toUser(user));
    }

    @Override
    public User saveUser(User user) {
        if (findByUsername(user.getUsername()).isPresent()){
            return null;
        }
        // Define user
        UserRepresentation newUser = new UserRepresentation();
        newUser.setEnabled(true);
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setAttributes(Collections.singletonMap("avatar", Arrays.asList("demo")));

        UsersResource usersRessource = keycloakClient
                .realm(realm)
                .users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(newUser);

        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        user.setId(userId);
        System.out.printf("User created with userId: %s%n", userId);
        return user;
    }


    private User toUser(UserRepresentation userRepresentation) {
        return new User(userRepresentation.getId(), userRepresentation.getUsername(),
                userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName()
        , getAttributeAvatar(userRepresentation));
    }

    private String getAttributeAvatar(UserRepresentation userRepresentation){
        try{
            return userRepresentation.firstAttribute("avatar");
        }catch (Exception e){
            return "";
        }

    }
}
