package com.utm.specsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.models.Spec;
import com.utm.specsys.repositories.SpecRepository;
import com.utm.specsys.services.FileLocationService;
import com.utm.specsys.services.KeycloakService;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileLocationService fileLocationService;

    @Autowired
    SpecRepository specRepository;

    @Autowired
    KeycloakService kcAdminClient;

    @PostMapping(value = "/users/{userId}/specs/{specId}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Long uploadFile(@RequestParam MultipartFile file, @PathVariable String userId, @PathVariable Long specId) throws Exception {

        Spec spec = specRepository.findByIdAndUserId(specId, userId).orElseThrow(() ->  new SpecNotFoundForUserException(specId, userId));

        Long result = fileLocationService.save(file.getBytes(), file.getOriginalFilename(), spec);

        kcAdminClient.CreateFile(file.getOriginalFilename(), specId, userId);
        
        return result;
    }

    @PutMapping(value = "/users/{userId}/specs/{specId}/files/{fileName}")
    Long replaceFile(@RequestParam MultipartFile file, @PathVariable String fileName, @PathVariable String userId, @PathVariable Long specId) throws Exception {
        return fileLocationService.replace(file.getBytes(), file.getOriginalFilename(), userId, specId, fileName);
    }

    @GetMapping(value = "/users/{userId}/specs/{specId}/files/{fileName}")
    FileSystemResource getFileByName(@PathVariable String fileName, @PathVariable String userId, @PathVariable Long specId) throws Exception {
        return fileLocationService.find(userId, specId, fileName);
    }

    @GetMapping(value = "/users/{userId}/specs/{specId}/files")
    List<String> getFileNames(@PathVariable String userId, @PathVariable Long specId) throws Exception {
        return fileLocationService.findAllFileNames(userId, specId);
    }
}
