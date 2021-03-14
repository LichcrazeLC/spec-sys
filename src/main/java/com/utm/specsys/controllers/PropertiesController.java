package com.utm.specsys.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utm.specsys.repositories.*;
import com.utm.specsys.models.*;
import com.utm.specsys.exceptions.*;
import com.utm.specsys.models.ObjectProperty;

@RestController
public class PropertiesController {

    private final PropertyRepository repository;

    PropertiesController(PropertyRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/properties")
    List<Property> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/properties/{id}")
    Property one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
    }

    @PostMapping("/properties")
    Property newParentProperty(@RequestBody Property property) {
        return repository.save(property);
    }

    @PostMapping("/properties/{id}/childProperties")
    List<Property> newChildProperty(@RequestBody List<Property> properties, @PathVariable Long id) {
        Property parentProperty = repository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
        for (Property property : properties) {
            property.setParentProperty(parentProperty);
        }
         
        ((ObjectProperty) parentProperty).setProperties(properties);
        return repository.saveAll(properties);
    }

}
