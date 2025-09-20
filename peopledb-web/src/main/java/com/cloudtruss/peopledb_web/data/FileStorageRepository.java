package com.cloudtruss.peopledb_web.data;

import com.cloudtruss.peopledb_web.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class FileStorageRepository {
    // STORAGE_FOLDER is an env variable, it is the absolute path to the folder where the images are stored.
    // it was set in the IDE in the configurations window cmd + options + R
    @Value("${STORAGE_FOLDER}")
    private String storageFolder;

    public void save(String originalFilename, InputStream inputStream) {
        try {
            Path filePath = Path.of(storageFolder).resolve(originalFilename).normalize();// concatenate paths together
            Files.copy(inputStream, filePath); // copy or write the InputStream (file) to the file system
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    public Resource findByName(String filename) {
        try {
            Path filePath = Path.of(storageFolder).resolve(filename).normalize();
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e);
        }
    }

    // deleting file from the file system
    public void deleteAllByName(Collection<String> photoFileNames) {
        try {
            for (String photoFileName : photoFileNames.stream().filter(f -> f != null).collect(Collectors.toSet())) {
                Path filePath = Path.of(storageFolder).resolve(photoFileName).normalize();
                Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {
            throw new StorageException(e);
        }

    }
}
