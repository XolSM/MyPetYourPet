package com.example.mypetyourpet.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class FileStorageService {

    private final Cloudinary cloudinary;

    public FileStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map<String, String> uploadFile(MultipartFile file) {
        try{
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();

            Map<String, String> resultInfo = new HashMap<>();
            resultInfo.put("url", url);
            resultInfo.put("publicId", publicId);
            return resultInfo;

        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public void deleteOldImage(String profilePicturePublicId) {
        try{
            cloudinary.uploader().destroy(profilePicturePublicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }


}
