package com.utm.specsys.repositories;

import java.util.List;
import java.util.Optional;
import com.utm.specsys.models.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbRepository extends JpaRepository<File, Long> {
    List<File> findBySpecId(Long specId);
    Optional<File> findByIdAndSpecId(Long id, Long specId);
    Optional<File> findByNameAndSpecId(String name, Long specId);
    Boolean existsByName(String name);
}
