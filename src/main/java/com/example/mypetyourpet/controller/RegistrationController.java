package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //this is what tells Spring that the class handles http requests
@RequestMapping("/api/registration")
//@CrossOrigin(origins = "http://localhost:3000/Register")
public class RegistrationController {

    private final RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;

    }

    @PostMapping("/petOwner")
    public ResponseEntity<PetOwnerUser> registerPetOwner
            (@RequestBody RegistrationRequest ownerRegistrationRequest) {
        PetOwnerUser owner = registrationService.registerPetOwner(ownerRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }

    //here I will add the post mapping for pet seeker
    @PostMapping("/petSeeker")
    public ResponseEntity<PetSeekerUser> registerPetSeeker
    (@RequestBody RegistrationRequest seekerRegistrationRequest) {
        PetSeekerUser seeker = registrationService.registerPetSeeker(seekerRegistrationRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(seeker);
    }
}
