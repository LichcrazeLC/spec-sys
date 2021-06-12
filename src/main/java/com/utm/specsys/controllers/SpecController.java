package com.utm.specsys.controllers;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.exceptions.UserNotFoundException;
import com.utm.specsys.models.Spec;
import com.utm.specsys.repositories.SpecRepository;
import com.utm.specsys.services.KeycloakService;

@CrossOrigin
@RestController
public class SpecController {

    @Autowired
    SpecRepository specRepository;

    @Autowired
    KeycloakService kcAdminClient;

    @GetMapping("/users/{userId}/specs")
    List<Spec> all(@PathVariable String userId) {
        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);
        if (foundUser != null) {
            return specRepository.findByUserId(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @PostMapping("/users/{userId}/specs")
    Spec newSpec(@PathVariable String userId, @RequestBody Spec newSpec, @RequestHeader("Authorization") String authHeader) {
        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);
        if (foundUser != null) {
            newSpec.setUserId(userId);
            Spec justCreated = specRepository.save(newSpec);
            kcAdminClient.CreateFilesResource(foundUser, justCreated.getId(), authHeader);
            kcAdminClient.CreateZipContentResource(foundUser, justCreated.getId(), authHeader);
            kcAdminClient.CreateSpec(newSpec.getName(), justCreated.getId(), foundUser, authHeader);
            return justCreated;
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    // Single item
    @GetMapping("/users/{userId}/specs/{id}")
    Spec one(@PathVariable String userId, @PathVariable Long id) {
        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);
        if (foundUser != null) {
            return specRepository.findByIdAndUserId(id, userId)
                    .orElseThrow(() -> new SpecNotFoundForUserException(id, userId));
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @PutMapping("/users/{userId}/specs/{id}")
    Spec replaceSpec(@RequestBody Spec newSpec, @PathVariable String userId, @PathVariable Long id) {

        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);
        if (foundUser != null) {
            return specRepository.findByIdAndUserId(id, userId).map(Spec -> {
                Spec.setName(newSpec.getName());
                return specRepository.save(Spec);
            }).orElseThrow(() -> new SpecNotFoundForUserException(id, userId));
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @DeleteMapping("/users/{userId}/specs/{id}")
    public ResponseEntity<?> deleteSpec(@PathVariable String userId, @PathVariable Long id) {
        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);
        if (foundUser != null) {
            return specRepository.findByIdAndUserId(id, userId).map(spec -> {
                specRepository.delete(spec);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new SpecNotFoundForUserException(id, userId));
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
