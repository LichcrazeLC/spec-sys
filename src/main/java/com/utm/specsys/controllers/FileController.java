package com.utm.specsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.utm.specsys.services.FileLocationService;


@RestController
public class FileController {

    @Autowired
    FileLocationService fileLocationService;

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Long uploadImage(@RequestParam MultipartFile file) throws Exception {
        return fileLocationService.save(file.getBytes(), file.getOriginalFilename());
    }

    @GetMapping(value = "/files/{fileId}")
    FileSystemResource downloadImage(@PathVariable Long fileId) throws Exception {
        return fileLocationService.find(fileId);
    }
}
