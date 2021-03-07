package com.utm.specsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utm.specsys.models.*;

public interface SpecRepository extends JpaRepository<Spec, Long> {

}