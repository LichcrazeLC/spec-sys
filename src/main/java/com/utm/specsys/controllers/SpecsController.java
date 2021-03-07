package com.utm.specsys.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utm.specsys.repositories.*;
import com.utm.specsys.models.*;
import com.utm.specsys.exceptions.*;

@RestController
public class SpecsController {

    private final SpecRepository repository;

    SpecsController(SpecRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/specs")
    List<Spec> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/specs/{id}")
    Spec one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new SpecNotFoundException(id));
    }

}
