package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.service.FileStorageService;
import com.example.mypetyourpet.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController //this is what tells Spring that the class handles http requests
@RequestMapping("/api/registration")
//@CrossOrigin(origins = "http://localhost:3000/Register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final FileStorageService fileStorageService;


//    public RegistrationController(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//
//    }

//    @PostMapping(value = "/petOwner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PetOwnerUser> registerPetOwner
//            (@RequestBody RegistrationRequest ownerRegistrationRequest) {
//        PetOwnerUser owner = registrationService.registerPetOwner(ownerRegistrationRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
//    }

    @PostMapping(value = "/petOwner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerPetOwner
            (@RequestPart("OwnerRegistrationRequest") RegistrationRequest ownerRegistrationRequest,
             @RequestPart("file") MultipartFile file) {

//        for debugging
//        System.out.println("Owner DTO: " + ownerRegistrationRequest);
//        System.out.println("File name: " + file.getOriginalFilename());
//        System.out.println("File empty: " + file.isEmpty());

        if(file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Pet picture is required."));
        }

        Long maxFileSize = 2*1024*1024L;
        if(file.getSize() > maxFileSize) {
            return ResponseEntity.badRequest().body(Map.of("error", "File size is too large, should be less than 2MB."));
        }

        String contentType = file.getContentType();

        if (!List.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid image format."));
        }

        Map<String, String> uploadResult = fileStorageService.uploadFile(file);
        System.out.println("Cloudinary upload result: " + uploadResult);
        ownerRegistrationRequest.setProfilePic(uploadResult.get("url"));
        ownerRegistrationRequest.setProfilePicturePublicId(uploadResult.get("publicId"));
        PetOwnerUser owner = registrationService.registerPetOwner(ownerRegistrationRequest);
        return ResponseEntity.ok(owner);
    }


    @PostMapping(value = "/petSeeker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerPetSeeker
            (@RequestPart("OwnerRegistrationRequest") RegistrationRequest seekerRegistrationRequest,
             @RequestPart("file") MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Pet picture is required."));
        }

        Long maxFileSize = 2*1024*1024L;
        if(file.getSize() > maxFileSize) {
            return ResponseEntity.badRequest().body(Map.of("error", "File size is too large, should be less than 2MB."));
        }

        String contentType = file.getContentType();

        if (!List.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid image format."));
        }

        Map<String, String> uploadResult = fileStorageService.uploadFile(file);
        System.out.println("Cloudinary upload result: " + uploadResult);
        seekerRegistrationRequest.setProfilePic(uploadResult.get("url"));
        seekerRegistrationRequest.setProfilePicturePublicId(uploadResult.get("publicId"));
        PetSeekerUser seeker = registrationService.registerPetSeeker(seekerRegistrationRequest);
        return ResponseEntity.ok(seeker);
    }
}
