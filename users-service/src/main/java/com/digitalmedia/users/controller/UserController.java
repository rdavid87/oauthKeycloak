package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.service.IKeycloakService;
import com.digitalmedia.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {
  private final IUserService userService;
 //TODO  estos dos endpoints funcionaran cuando este configurada la seguridad en el proyecto

  private final IKeycloakService keycloakService;

  @Autowired
  public UserController(IUserService userService, IKeycloakService keycloakService) {
    this.userService = userService;
    this.keycloakService = keycloakService;
  }

  @GetMapping("/info")
  public String getInfo(){
    return "Te has conectado al microservicio de Usuarios";
  }

  @GetMapping("/admin/list")
  @PreAuthorize("hasAuthority('ROLE_admin')")
  public List<User> getAllNoAdmin() {
    return keycloakService.getAllNoAdmin();
  }

  @GetMapping("/id/{id}")
  public Optional<User> findById(@PathVariable String id) {
    return keycloakService.findById(id);
  }

  @GetMapping("/admin/{username}")
  @PreAuthorize("hasAuthority('ROLE_admin')")
  public Optional<User> findByUsername(@PathVariable String username) {
    return keycloakService.findByUsername(username);
  }

  @PutMapping("/update")
  @PreAuthorize("hasAuthority('ROLE_admin')")
  public Optional<User> updateUser(@RequestParam String id, @RequestParam String avatar) {
    return keycloakService.updateAvatar(id, avatar);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ROLE_admin')")
  public User createUser(@Valid @RequestBody User user){
    return keycloakService.saveUser(user);
  }

  @GetMapping("/me")
  public User getMe(Principal principal){
    return keycloakService.findByUsername(principal.getName()).get();
  }

  @PutMapping("/me/update")
  public User updateMe(@Valid @RequestBody User user, Principal principal){
    User me = keycloakService.findByUsername(principal.getName()).get();
    me.setAvatar(user.getAvatar());
    me.setEmail(user.getEmail());
    me.setFirstName(user.getFirstName());
    me.setLastName(user.getLastName());

    return keycloakService.saveUser(me);
  }

}
