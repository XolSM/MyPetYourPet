package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
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
    private final PetSeekerUserRepository petSeekerUserRepository;
    private final PetOwnerUserRepository petOwnerUserRepository;



@PostMapping(value = "/petOwner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> registerPetOwner
(@RequestPart("RegistrationRequest") RegistrationRequest ownerRegistrationRequest,
 @RequestPart("Picture") MultipartFile picture,
 @RequestPart("GovId") MultipartFile govId,
 @RequestPart("BackCheck") MultipartFile backCheck) {

//        for debugging
//        System.out.println("Owner DTO: " + ownerRegistrationRequest);
//        System.out.println("File name: " + file.getOriginalFilename());
//        System.out.println("File empty: " + file.isEmpty());

    Map<String, String> uploadResultPicture = fileStorageService.uploadFile(picture);
    System.out.println("Cloudinary picture upload result Picture: " + uploadResultPicture);
    ownerRegistrationRequest.setProfilePic(uploadResultPicture.get("url"));
    ownerRegistrationRequest.setProfilePicturePublicId(uploadResultPicture.get("publicId"));
    Map<String, String> uploadResultGovId = fileStorageService.uploadFile(govId);
    ownerRegistrationRequest.setGovernmentId(uploadResultGovId.get("url"));
    System.out.println("Cloudinary upload result govid: " + uploadResultGovId);
    Map<String, String> uploadResultBackCheck = fileStorageService.uploadFile(backCheck);
    ownerRegistrationRequest.setBackgroundCheck(uploadResultBackCheck.get("url"));
    System.out.println("Cloudinary upload result backcheck: " + uploadResultBackCheck);
    PetOwnerUser owner = registrationService.registerPetOwner(ownerRegistrationRequest);
    return ResponseEntity.ok(owner);
}


    @PostMapping(value = "/petSeeker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerPetSeeker
            (@RequestPart("RegistrationRequest") RegistrationRequest seekerRegistrationRequest,
             @RequestPart("Picture") MultipartFile picture,
             @RequestPart("GovId") MultipartFile govId,
             @RequestPart("BackCheck") MultipartFile backCheck) {

//        for debugging
//        System.out.println("Owner DTO: " + ownerRegistrationRequest);
//        System.out.println("File name: " + file.getOriginalFilename());
//        System.out.println("File empty: " + file.isEmpty());

        Map<String, String> uploadResultPicture = fileStorageService.uploadFile(picture);
        System.out.println("Cloudinary picture upload result Picture: " + uploadResultPicture);
        seekerRegistrationRequest.setProfilePic(uploadResultPicture.get("url"));
        seekerRegistrationRequest.setProfilePicturePublicId(uploadResultPicture.get("publicId"));
        Map<String, String> uploadResultGovId = fileStorageService.uploadFile(govId);
        seekerRegistrationRequest.setGovernmentId(uploadResultGovId.get("url"));
        System.out.println("Cloudinary upload result govid: " + uploadResultGovId);
        Map<String, String> uploadResultBackCheck = fileStorageService.uploadFile(backCheck);
        seekerRegistrationRequest.setBackgroundCheck(uploadResultBackCheck.get("url"));
        System.out.println("Cloudinary upload result backcheck: " + uploadResultBackCheck);
        PetOwnerUser seeker = registrationService.registerPetOwner(seekerRegistrationRequest);
        return ResponseEntity.ok(seeker);
    }

    @PostMapping(value = "/updatePetSeeker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePetSeeker
            (@RequestPart("ProfileUpdateRequest") PetSeekerUser seeker,
             @RequestPart(value = "file", required = false) MultipartFile file) {
        PetSeekerUser userToUpdate = petSeekerUserRepository.findById(seeker.getId()).orElseThrow();
        if(file != null && !file.isEmpty()) {

            Long maxFileSize = 2 * 1024 * 1024L;
            if (file.getSize() > maxFileSize) {
                return ResponseEntity.badRequest().body(Map.of("error", "File size is too large, should be less than 2MB."));
            }

            String contentType = file.getContentType();

            if (!List.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid image format."));
            }

            if (userToUpdate.getProfilePicturePublicId() != null) {
                fileStorageService.deleteOldImage(userToUpdate.getProfilePicturePublicId());
            }

            Map<String, String> uploadResult = fileStorageService.uploadFile(file);
            System.out.println("Cloudinary upload result: " + uploadResult);
            userToUpdate.setProfilePicture(uploadResult.get("url"));
            userToUpdate.setProfilePicturePublicId(uploadResult.get("publicId"));
        }

        userToUpdate.getCustomerInfo().setAge(seeker.getCustomerInfo().getAge());
        userToUpdate.setFullName(seeker.getFullName());
        userToUpdate.getCustomerInfo().setPhone(seeker.getCustomerInfo().getPhone());
        userToUpdate.getCustomerInfo().setLocation(seeker.getCustomerInfo().getLocation());
        userToUpdate.getCustomerInfo().setGender(seeker.getCustomerInfo().getGender());
        userToUpdate.getCustomerInfo().setBio(seeker.getCustomerInfo().getBio());
        petSeekerUserRepository.save(userToUpdate);
        return ResponseEntity.ok(userToUpdate);
    }

    @PostMapping(value = "/updatePetOwner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePetOwner
            (@RequestPart("ProfileUpdateRequest") PetOwnerUser owner,
             @RequestPart(value = "file", required = false) MultipartFile file) {
        PetOwnerUser userToUpdate = petOwnerUserRepository.findById(owner.getId()).orElseThrow();
        if(file != null && !file.isEmpty()) {

            Long maxFileSize = 2 * 1024 * 1024L;
            if (file.getSize() > maxFileSize) {
                return ResponseEntity.badRequest().body(Map.of("error", "File size is too large, should be less than 2MB."));
            }

            String contentType = file.getContentType();

            if (!List.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid image format."));
            }

            if (userToUpdate.getProfilePicturePublicId() != null) {
                fileStorageService.deleteOldImage(userToUpdate.getProfilePicturePublicId());
            }

            Map<String, String> uploadResult = fileStorageService.uploadFile(file);
            System.out.println("Cloudinary upload result: " + uploadResult);
            userToUpdate.setProfilePicture(uploadResult.get("url"));
            userToUpdate.setProfilePicturePublicId(uploadResult.get("publicId"));
        }

        userToUpdate.getCustomerInfo().setAge(owner.getCustomerInfo().getAge());
        userToUpdate.setFullName(owner.getFullName());
        userToUpdate.getCustomerInfo().setPhone(owner.getCustomerInfo().getPhone());
        userToUpdate.getCustomerInfo().setLocation(owner.getCustomerInfo().getLocation());
        userToUpdate.getCustomerInfo().setGender(owner.getCustomerInfo().getGender());
        userToUpdate.getCustomerInfo().setBio(owner.getCustomerInfo().getBio());
        petOwnerUserRepository.save(userToUpdate);
        return ResponseEntity.ok(userToUpdate);
    }
}
