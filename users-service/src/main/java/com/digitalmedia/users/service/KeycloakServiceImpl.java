package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.repository.IKeycloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeycloakServiceImpl implements IKeycloakService{

    private final IKeycloakRepository keycloakRepository;

    @Autowired
    public KeycloakServiceImpl(IKeycloakRepository keycloakRepository) {
        this.keycloakRepository = keycloakRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return keycloakRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(keycloakRepository.findById(id).get());
    }

    @Override
    public List<User> getAllNoAdmin() {
        return keycloakRepository.getAllNoAdmin();
    }

    @Override
    public Optional<User> updateAvatar(String id, String avatar) {
        return Optional.of(keycloakRepository.updateAvatar(id, avatar).get());
    }

    @Override
    public User saveUser(User user) {
        return keycloakRepository.saveUser(user);
    }
}
