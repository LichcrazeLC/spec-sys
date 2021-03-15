package com.utm.specsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.utm.specsys.exceptions.SpecNotFoundException;
import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.models.Spec;
import com.utm.specsys.models.User;
import com.utm.specsys.repositories.SpecRepository;
import com.utm.specsys.repositories.UserRepository;

@RestController
public class SpecController {

    @Autowired
    SpecRepository specRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{userId}/specs")
    List<Spec> all(@PathVariable Long userId) {
        return specRepository.findByUserId(userId);
    }

    @PostMapping("/users/{userId}/specs")
    Spec newSpec(@PathVariable Long userId, @RequestBody Spec newSpec) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SpecNotFoundException(userId));
        newSpec.setUser(user);
        return specRepository.save(newSpec);
    }

    // Single item
    @GetMapping("/users/{userId}/specs/{id}")
    Spec one(@PathVariable Long userId, @PathVariable Long id) {

        return specRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new SpecNotFoundForUserException(id, userId));
    }

    @PutMapping("/users/{userId}/specs/{id}")
    Spec replaceSpec(@RequestBody Spec newSpec, @PathVariable Long userId, @PathVariable Long id) {

        return specRepository.findByIdAndUserId(id, userId).map(Spec -> {
            Spec.setName(newSpec.getName());
            return specRepository.save(Spec);
        }).orElseThrow(() -> 
            new SpecNotFoundForUserException(id, userId)
        );
    }

    @DeleteMapping("/users/{userId}/specs/{id}")
    public ResponseEntity<?> deleteSpec(@PathVariable Long userId, @PathVariable Long id) {
        return specRepository.findByIdAndUserId(id, userId).map(spec -> {
            specRepository.delete(spec);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new SpecNotFoundForUserException(id, userId));
    }
}
