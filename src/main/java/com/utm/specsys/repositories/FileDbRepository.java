package com.utm.specsys.repositories;

import com.utm.specsys.models.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbRepository extends JpaRepository<File, Long> {}
