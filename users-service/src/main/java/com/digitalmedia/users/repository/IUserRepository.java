package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;

import java.util.Optional;

public interface IUserRepository {

  Optional<User> validateAndGetUser(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);
}
