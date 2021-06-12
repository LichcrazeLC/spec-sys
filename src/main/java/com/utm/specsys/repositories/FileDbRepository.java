package com.utm.specsys.repositories;

import java.util.List;
import java.util.Optional;
import com.utm.specsys.models.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FileDbRepository extends JpaRepository<File, Long> {

    @Query(value= "select f.name from file f where spec_id = ?1", nativeQuery= true)
    List<String> findAllFileNamesBySpecId(Long specId);

    @Query(value= "select f.name from file f where spec_id = ?1 and f.name LIKE ?2%", nativeQuery= true)
    List<String> findAllFileNamesBySpecIdAndType(Long specId, String fileType);

    List<File> findBySpecId(Long specId);
    Optional<File> findByIdAndSpecId(Long id, Long specId);
    Optional<File> findByNameAndSpecId(String name, Long specId);
    Boolean existsByName(String name);
}
