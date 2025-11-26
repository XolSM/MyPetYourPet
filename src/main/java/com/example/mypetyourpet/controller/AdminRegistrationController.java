package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.AdminRegistrationRequest;
import com.example.mypetyourpet.model.AdministratorUser;
import com.example.mypetyourpet.repository.SupportUserRepository;
import com.example.mypetyourpet.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class AdminRegistrationController {

    private final SupportUserRepository supportUserRepository;
    private final CloudinaryService cloudinaryService; // Only if using image upload

    @PostMapping(value = "/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerAdmin(
            @RequestPart("RegistrationRequest") AdminRegistrationRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        try {
            String profileImageUrl = null;
            String publicId = null;

            // Optional: If admin uploaded a profile picture
            if (file != null && !file.isEmpty()) {
                profileImageUrl = cloudinaryService.upload(file);
                publicId = cloudinaryService.getPublicId(file);
            }

            // Save admin user
            AdministratorUser admin = new AdministratorUser();
            admin.setFirebaseuid(request.getFirebaseUID());
            admin.setFullName(request.getFullName());
            admin.setEmail(request.getEmail());
            admin.setUserType(request.getUserType()); // default "Administrator"
            admin.setProfilePicture(profileImageUrl);
            admin.setProfilePicturePublicId(publicId);

            supportUserRepository.save(admin);

            return ResponseEntity.ok("Admin account created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating admin account: " + e.getMessage());
        }
    }
}
