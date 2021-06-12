package com.utm.specsys.controllers;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    Long uploadFile(@RequestParam MultipartFile file, @PathVariable String userId, @PathVariable Long specId, @RequestHeader("Authorization") String authHeader) throws Exception {

        Spec spec = specRepository.findByIdAndUserId(specId, userId).orElseThrow(() ->  new SpecNotFoundForUserException(specId, userId));

        Long result = fileLocationService.save(file.getBytes(), file.getOriginalFilename(), spec);

        UserRepresentation foundUser = kcAdminClient.GetUserById(userId);

        kcAdminClient.CreateFile(file.getOriginalFilename(), specId, foundUser, authHeader);
        
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
    List<String> getFileNames(@PathVariable String userId, @PathVariable Long specId, @RequestParam(required = false) String fileType, @RequestParam(required = false) String fileContent) throws Exception {
        if (fileType == null && fileContent == null){
            return fileLocationService.findAllFileNames(userId, specId);
        } 
        else {
            return fileLocationService.findAllFileNamesByType(userId, specId, fileType);
        }

    }

    @GetMapping(value = "/users/{userId}/specs/{specId}/zipContent")
    FileOutputStream getFileContent(@PathVariable String userId, @PathVariable Long specId) throws Exception {

        List<FileSystemResource> specFiles = fileLocationService.findAll(userId, specId);
        FileOutputStream fos = new FileOutputStream("Compressed" + specId + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (FileSystemResource srcFile : specFiles) {
            InputStream fis = srcFile.getInputStream();
            ZipEntry zipEntry = new ZipEntry(srcFile.getFilename());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
        return fos;
    }
}
