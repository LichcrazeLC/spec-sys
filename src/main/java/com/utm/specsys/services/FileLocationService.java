package com.utm.specsys.services;

import com.utm.specsys.models.File;
import com.utm.specsys.repositories.FileDbRepository;
import com.utm.specsys.repositories.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileLocationService {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileDbRepository fileDbRepository;

    public Long save(byte[] bytes, String fileName) throws Exception {
        String location = fileRepository.save(bytes, fileName);

        return fileDbRepository.save(new File(fileName, location))
            .getId();
    }

    public FileSystemResource find(Long imageId) {
        File file = fileDbRepository.findById(imageId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    
        return fileRepository.findInFileSystem(file.getLocation());
    }
}