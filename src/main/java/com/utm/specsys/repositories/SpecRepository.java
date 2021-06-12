package com.utm.specsys.repositories;

import java.util.List;
import java.util.Optional;

import com.utm.specsys.models.Spec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Long> {
    List<Spec> findByUserId(String userId);
    Optional<Spec> findByIdAndUserId(Long id, String userId);
    Boolean existsByIdAndUserId(Long id, String userId);

    @Query(value= "select * from spec where is_public=1", nativeQuery= true)
    List<Spec> findAllPublicSpecs();
}
