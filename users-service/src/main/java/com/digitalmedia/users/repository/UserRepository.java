package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepository implements IUserRepository {

  private final KeycloakRepositoryImpl keycloakRepository;

  @Autowired
  public UserRepository(KeycloakRepositoryImpl keycloakRepository) {
    this.keycloakRepository = keycloakRepository;
  }

  @Override
  public Optional<User> validateAndGetUser(String username) {
    return  keycloakRepository.findByUsername(username).stream().findFirst();
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return keycloakRepository.findByUsername(username).stream().findFirst();
  }

  @Override
  public User saveUserExtra(User user) {
    return keycloakRepository.saveUser(user);
  }
}
