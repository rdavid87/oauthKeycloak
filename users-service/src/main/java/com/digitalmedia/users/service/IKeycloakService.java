package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;

import java.util.List;
import java.util.Optional;

public interface IKeycloakService {

    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    List<User> getAllNoAdmin();
    Optional<User> updateAvatar(String id, String nationality);

    User saveUser(User user);
}

