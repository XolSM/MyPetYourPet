package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetProfileStatus;
import com.example.mypetyourpet.repository.PetRepository;
import com.example.mypetyourpet.service.FileStorageService;
import com.example.mypetyourpet.service.PetService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pets") // or /api/pets
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;
    private final FileStorageService fileStorageService;

//    @PostMapping("/{petId}/petPicture")
//    public ResponseEntity<Map<String,String>> uploadPetPicture(@PathVariable Long petId,
//                                                               @RequestParam("file") MultipartFile file) {
//        String imageUrl = fileStorageService.save(file, petId);
//        petService.updatePetPictureUrl(petId, imageUrl);
//
//        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
//    }
    @PostMapping(value = "/pets", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        String imageUrl = fileStorageService.save(file, savedPet.getPetId());

        savedPet.setProfilePicture(imageUrl);

        return ResponseEntity.ok(savedPet);
    }
//    @PostMapping("/{petId}/petPicture")
//    public ResponseEntity<?> uploadPetPicture(@PathVariable Long petId,
//                                              @RequestParam("file") MultipartFile file) {
//        if(file.isEmpty()) {
//            return ResponseEntity.badRequest().body(Map.of("error", "No file uploaded."));
//        }
//
//        Long maxFileSize = 2*1024*1024L;
//        if(file.getSize() == maxFileSize) {
//            return ResponseEntity.badRequest().body(Map.of("error", "File size is too" +
//                    " large, should be less than 2MB."));
//        }
//
//        String contentType = file.getContentType();
//        if(contentType == null || !contentType.equalsIgnoreCase("image/jpeg")
//        || !contentType.equalsIgnoreCase("image/png")
//        || !contentType.equalsIgnoreCase("image/jpg")) {
//            return ResponseEntity.badRequest().body(Map.of("error", "Invalid file " +
//                    "type, only JPEG, PNG, JPG are supported."));
//        }
//        String imageUrl = fileStorageService.save(file, petId);
//        petService.updatePetPictureUrl(petId, imageUrl);
//
//        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
//    }

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

        Pet petToUpdate = petService.getPetById(petId);
        String imageUrl = fileStorageService.update(file, petId, petToUpdate.getProfilePicture());
        petService.updatePetPictureUrl(petId, imageUrl);

        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }

//    public PetController(PetService petService, FileStorageService fileStorageService) {
//        this.petService = petService;
//        this.fileStorageService = fileStorageService;
//    }

//    @PostMapping("/pets")
//    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
//        Pet savedPet  = petService.createPet(pet);
//        return ResponseEntity.ok(savedPet);
//    }

    //SHOULD WE CREATE DIFFERENT METHODS ??
    // TO GET ALL CUSTOMER PETS TO LIST IN THEIR PAGE
    // TO GET ALL PETS IN THE DB TO LIST TO THE SEEKER
    @GetMapping
    public List<Pet> petList(@RequestParam(required = false) Long customerId, String petName,
                             PetProfileStatus petProfileStatus){
        return petService.petList(customerId, petName, petProfileStatus);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Map<String, String>> deletePet(@PathVariable("petId") Long petId){
        petService.deletePetByPetId(petId);
        return ResponseEntity.ok(Map.of("message", "Pet deleted successfully"));
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable("petId") Long petId, @RequestBody Pet pet){
        Pet updatedPet = petService.updatePet(petId, pet);
        return ResponseEntity.ok(updatedPet);
    }

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
