package com.utm.specsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.models.Spec;
import com.utm.specsys.repositories.SpecRepository;
import com.utm.specsys.services.FileLocationService;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileLocationService fileLocationService;

    @Autowired
    SpecRepository specRepository;

    @PostMapping(value = "/users/{userId}/specs/{specId}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Long uploadFile(@RequestParam MultipartFile file, @PathVariable Long userId, @PathVariable Long specId) throws Exception {

        Spec spec = specRepository.findByIdAndUserId(specId, userId).orElseThrow(() ->  new SpecNotFoundForUserException(specId, userId));
        
        return fileLocationService.save(file.getBytes(), file.getOriginalFilename(), spec);
    }

    @PutMapping(value = "/users/{userId}/specs/{specId}/files/{fileName}")
    Long replaceFile(@RequestParam MultipartFile file, @PathVariable String fileName, @PathVariable Long userId, @PathVariable Long specId) throws Exception {
        return fileLocationService.replace(file.getBytes(), file.getOriginalFilename(), userId, specId, fileName);
    }

    @GetMapping(value = "/users/{userId}/specs/{specId}/files/{fileName}")
    FileSystemResource getFileByName(@PathVariable String fileName, @PathVariable Long userId, @PathVariable Long specId) throws Exception {
        return fileLocationService.find(userId, specId, fileName);
    }
}
