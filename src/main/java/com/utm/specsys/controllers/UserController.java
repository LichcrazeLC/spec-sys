package com.utm.specsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utm.specsys.models.User;
import com.utm.specsys.services.KeycloakService;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    KeycloakService kcAdminClient;

    @GetMapping("/users")
    ResponseEntity<?> all() {
        return kcAdminClient.GetAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
       return kcAdminClient.SaveUser(newUser);
    }

    @PostMapping("/users/signin")
    public ResponseEntity<?> logIn(@RequestBody User registeredUser) {
       return kcAdminClient.SignIn(registeredUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable String id) {
        return ResponseEntity.ok(kcAdminClient.GetUserById(id));
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void replaceUser(@RequestBody User newUser, @PathVariable String id) {

        kcAdminClient.UpdatePassword(newUser.getPassword(), id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        // TO DO
    }
}
