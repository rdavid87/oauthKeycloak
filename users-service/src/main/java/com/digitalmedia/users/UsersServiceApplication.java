package com.digitalmedia.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
public class UsersServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsersServiceApplication.class, args);
  }

}
