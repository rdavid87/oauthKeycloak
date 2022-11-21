package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;

import java.util.List;
import java.util.Optional;

public interface IKeycloakRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    List<User> getAllNoAdmin();
    Optional<User> updateAvatar(String id, String avatar);

    User saveUser(User user);
}
