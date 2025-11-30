package com.example.mypetyourpet.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {

    public String upload(MultipartFile file) {

        return "https://cloudinary.com/fake-image-url";
    }

    public String getPublicId(MultipartFile file) {
        return "fake_public_id_123";
    }
}
