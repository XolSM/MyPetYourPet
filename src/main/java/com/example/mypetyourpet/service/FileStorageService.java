package com.example.mypetyourpet.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    @Value("${app.upload.dir}")
    private String uploadDir;

    public String save(MultipartFile file, Long petId) {
        try{
//            Path path = Paths.get(uploadDir + petId + ".jpg");
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String filename = "pet" + petId + "_" +
                    System.currentTimeMillis() + ".png";
            Path filePath = path.resolve(filename);
            Files.copy(file.getInputStream(), filePath,
                        StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + petId + filename;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
