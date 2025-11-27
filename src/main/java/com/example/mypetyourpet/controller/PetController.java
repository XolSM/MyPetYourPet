package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetProfileStatus;
import com.example.mypetyourpet.repository.PetRepository;
import com.example.mypetyourpet.service.FileStorageService;
import com.example.mypetyourpet.service.PetService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pets") // or /api/pets
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;
    private final FileStorageService fileStorageService;
    private final PetRepository petRepository;


    @PostMapping(value = "/updatePet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePet(@RequestPart("Pet") Pet pet,
                                       @RequestPart(value = "file", required = false) MultipartFile file) {
        Pet petToUpdate = petRepository.findPetByPetId(pet.getPetId()).orElseThrow();
        petToUpdate.setPetAge(pet.getPetAge());
        petToUpdate.setPetBehavior(pet.getPetBehavior());
        petToUpdate.setDewormingUpToDate(pet.isDewormingUpToDate());
        petToUpdate.setVaccinationUpToDate(pet.isVaccinationUpToDate());
        petToUpdate.setPetFee(pet.getPetFee());
        petToUpdate.setPetProfileStatus(pet.getPetProfileStatus());

        if(file != null) {
            if(file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pet picture file is empty."));
            }

            Long maxFileSize = 2*1024*1024L;
            if(file.getSize() > maxFileSize) {
                return ResponseEntity.badRequest().body(Map.of("error", "File size is too large, should be less than 2MB."));
            }

            String contentType = file.getContentType();
            if (!List.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid image format."));
            }

            if (petToUpdate.getProfilePicturePublicId() != null) {
                fileStorageService.deleteOldImage(petToUpdate.getProfilePicturePublicId());
            }

            Map<String, String> uploadResult = fileStorageService.uploadFile(file);
            petToUpdate.setProfilePicture(uploadResult.get("url"));
            petToUpdate.setProfilePicturePublicId(uploadResult.get("publicId"));
        }
        petRepository.save(petToUpdate);
        return ResponseEntity.ok(petToUpdate);
    }

    @PostMapping(value = "/createPet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPet(@RequestPart("Pet") Pet pet,
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
        Pet tempPet = pet;
        Map<String, String> uploadResult = fileStorageService.uploadFile(file);
        System.out.println("Cloudinary upload result: " + uploadResult);
        tempPet.setProfilePicture(uploadResult.get("url"));
        tempPet.setProfilePicturePublicId(uploadResult.get("publicId"));
        Pet savedPet  = petService.createPet(tempPet);

        return ResponseEntity.ok(savedPet);
    }



    @GetMapping(value = "/getAllPets")
    public List<Pet> allPetsList(){
        return petRepository.findAll();
    }


    @DeleteMapping("/{petId}/deletePet")
    public ResponseEntity<Map<String, String>> deletePet(@PathVariable("petId") Long petId){
        petService.deletePetByPetId(petId);
        return ResponseEntity.ok(Map.of("message", "Pet deleted successfully"));
    }

    @GetMapping(value = "/{petId}/getPet")
    public Pet petListing(@PathVariable Long petId){
        return petService.getPetById(petId);
    }

    @GetMapping(value = "/{customerId}{petName}{petProfileStatus}/getPets")
    public List<Pet> petList(@RequestParam(required = false) Long customerId, String petName,
                                  PetProfileStatus petProfileStatus){
        return petService.petList(customerId, petName, petProfileStatus);
    }
    @GetMapping("/{customerId}/getOwnerPets")
    public List<Pet> ownerPetList(@PathVariable Long customerId){
        return petService.petOwnerList(customerId);
    }

}
