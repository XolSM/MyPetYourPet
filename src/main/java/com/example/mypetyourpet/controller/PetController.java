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

    @PostMapping(value = "/createPet2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPet2(@RequestPart("Pet") Pet pet) {
        Pet savedPet  = petService.createPet(pet);
        return ResponseEntity.ok(savedPet); // or change return type and add
        //return "redirect:/petProfile/" + petId;
    }

    @PostMapping(value = "/createPet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPet(@RequestPart("Pet") Pet pet,
                                         @RequestPart("file") MultipartFile file) {
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Pet picture" +
                    "is required."));
        }

        Long maxFileSize = 2*1024*1024L;
        if(file.getSize() == maxFileSize) {
            return ResponseEntity.badRequest().body(Map.of("error", "File size is too" +
                    " large, should be less than 2MB."));
        }

        String contentType = file.getContentType();
        if(contentType == null || !contentType.equalsIgnoreCase("image/jpeg")
                || !contentType.equalsIgnoreCase("image/png")
                || !contentType.equalsIgnoreCase("image/jpg")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid file " +
                    "type, only JPEG, PNG, JPG are supported."));
        }
        Pet savedPet  = petService.createPet(pet);
        Map<String, String> uploadResult = fileStorageService.uploadFile(file);
        savedPet.setProfilePicture(uploadResult.get("url"));
        savedPet.setProfilePicturePublicId(uploadResult.get("publicId"));

        return ResponseEntity.ok(savedPet); // or change return type and add
        //return "redirect:/petProfile/" + petId;
    }

    @PostMapping(value = "/{petId}/petPicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePetPicture(@PathVariable Long petId,
                                              @RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No file uploaded."));
        }

        Long maxFileSize = 2*1024*1024L;
        if(file.getSize() == maxFileSize) {
            return ResponseEntity.badRequest().body(Map.of("error", "File size is too" +
                    " large, should be less than 2MB."));
        }

        String contentType = file.getContentType();
        if(contentType == null || !contentType.equalsIgnoreCase("image/jpeg")
                || !contentType.equalsIgnoreCase("image/png")
                || !contentType.equalsIgnoreCase("image/jpg")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid file " +
                    "type, only JPEG, PNG, JPG are supported."));
        }

        Pet petToUpdate = petRepository.findPetByPetId(petId).orElseThrow();

        if (petToUpdate.getProfilePicturePublicId() != null) {
            fileStorageService.deleteOldImage(petToUpdate.getProfilePicturePublicId());
        }

        Map<String, String> uploadResult = fileStorageService.uploadFile(file);
        petToUpdate.setProfilePicture(uploadResult.get("url"));
        petToUpdate.setProfilePicturePublicId(uploadResult.get("publicId"));

        petRepository.save(petToUpdate);
        return ResponseEntity.ok(Map.of("imageUrl", uploadResult.get("url"))); //OR
        //return "redirect:/profile/" + petId;
    }

    @GetMapping(value = "/{customerId}{petName}/getPets")
    public List<Pet> petList(@RequestParam(required = false) Long customerId, String petName,
                             PetProfileStatus petProfileStatus){
        return petService.petList(customerId, petName, petProfileStatus);
    }

    @DeleteMapping("/{petId}/deletePet")
    public ResponseEntity<Map<String, String>> deletePet(@PathVariable("petId") Long petId){
        petService.deletePetByPetId(petId);
        return ResponseEntity.ok(Map.of("message", "Pet deleted successfully"));
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable("petId") Long petId, @RequestBody Pet pet){
        Pet updatedPet = petService.updatePet(petId, pet);
        return ResponseEntity.ok(updatedPet);
    }

    @GetMapping(value = "/{petId}/getPet")
    public Pet petListing(@PathVariable Long petId){
        return petService.getPetById(petId);
    }

    //OR
//    @GetMapping(value = "/getPet")
//    public Pet petListing(@RequestParam(required = false) Long petId){
//        return petService.getPetById(petId);
//    }




//    @PutMapping("/{petId}")
//    public ResponseEntity<Pet> updatePet(@PathVariable("petId") Long petId, @RequestBody Pet pet){
//        Pet updatedPet = petService.updatePet(petId, petBehavior, dewormingUpToDate, vaccinationUpToDate,
//                petFee, petProfileStatus);
//        return ResponseEntity.ok(updatedPet);
//    }




//    @PostMapping
//    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
//        if(petRepository.findByPetNameAndCustomerId(pet.getPetName(), pet.getCustomerId()).isPresent()){
//           throw new DataIntegrityViolationException("This Pet is already listed");
//        }
//        Pet newPet = petRepository.save(pet);
//        return ResponseEntity.ok(newPet);
//    }



//    @PostMapping
//    public ResponseEntity<?> createPet(@RequestBody Pet pet) {
//        try{
//            Pet newPet = petRepository.save(pet);
//            return ResponseEntity.ok(newPet);
//        }catch(DataIntegrityViolationException e){
//            return ResponseEntity.badRequest().body(Map.of("error","This Pet is already listed"));
//        }
//
//    }

    /*expected JASON
    "petName": "Navi",
    "petAge": "8",
    "petGender": "true",
    "petBreed": "Yorkshire",
    "petBehavior": "calm",
    "dewormingUpToDate": "true",
    "vaccinationUpToDate": "true",
    "petFee": "50",
    "profilePicture": "url",
    "customerId": "123L",
    "petProfileStatus": "PetProfileStatus.active" */

    /*Pet pet1 = new Pet("Navi", 8, true, "Yorkshire", "calm", true, true, 50, "url", 123L,PetProfileStatus.active);
     */


}
