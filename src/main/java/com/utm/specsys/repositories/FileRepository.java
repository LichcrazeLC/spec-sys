package com.utm.specsys.repositories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Repository
public class FileRepository {

    String RESOURCES_DIR = "C:\\Users\\cioca\\Documents\\Workspace\\Teza_Licenta\\spec-storage-fs\\";

    public String save(byte[] content, String fileName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + fileName);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return newFile.toAbsolutePath().toString();
    }

    public String update(byte[] content, String location, String newFileName, String oldFileName) {

        try {
            Path newFile = Paths.get(location);

            Files.write(newFile, content);

            if (!newFileName.equals(oldFileName)) {
                newFile = Files.move(newFile, newFile.getParent().resolve(new Date().getTime() + "-" + newFileName),
                        StandardCopyOption.REPLACE_EXISTING);
                return newFile.toAbsolutePath().toString();
            } else {
                return location;
            }
        } catch (Exception e) {
            // Handle file update problems.
            throw new RuntimeException();
        }

    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }
}