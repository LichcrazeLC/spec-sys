package com.utm.specsys.services;

import com.utm.specsys.exceptions.FileNotFoundForSpecException;
import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.models.File;
import com.utm.specsys.models.Spec;
import com.utm.specsys.repositories.FileDbRepository;
import com.utm.specsys.repositories.FileRepository;
import com.utm.specsys.repositories.SpecRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class FileLocationService {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileDbRepository fileDbRepository;
    @Autowired
    SpecRepository specRepository;

    public Long save(byte[] bytes, String fileName, Spec spec) throws Exception {
        String location = fileRepository.save(bytes, fileName);

        if (fileDbRepository.existsByName(fileName)) {
            throw new RuntimeException("Exists already!");
        }

        return fileDbRepository.save(new File(fileName, location, spec)).getId();
    }

    public FileSystemResource find(Long userId, Long specId, String fileName) {

        if (!specRepository.existsByIdAndUserId(specId, userId)) {
            throw new SpecNotFoundForUserException(specId, userId);
        }

        File file = fileDbRepository.findByNameAndSpecId(fileName, specId)
                .orElseThrow(() -> new FileNotFoundForSpecException(fileName, specId));

        return fileRepository.findInFileSystem(file.getLocation());
    }

    public Long replace(byte[] bytes, String newFileName, Long userId, Long specId, String fileName) {

        if (!specRepository.existsByIdAndUserId(specId, userId)) {
            throw new SpecNotFoundForUserException(specId, userId);
        }

        return fileDbRepository.findByNameAndSpecId(fileName, specId).map(File -> {

            String location = File.getLocation();
            String newLocation = fileRepository.update(bytes, location, newFileName, fileName);
            File.setName(newFileName);
            File.setLocation(newLocation);
            return fileDbRepository.save(File).getId();

        }).orElseThrow(() -> new FileNotFoundForSpecException(fileName, specId));

    }

}