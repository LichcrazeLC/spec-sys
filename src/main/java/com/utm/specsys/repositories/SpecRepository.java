package com.utm.specsys.repositories;

import java.util.List;
import java.util.Optional;

import com.utm.specsys.models.Spec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Long> {
    List<Spec> findByUserId(Long userId);
    Optional<Spec> findByIdAndUserId(Long id, Long userId);
    Boolean existsByIdAndUserId(Long id, Long userId);
}
