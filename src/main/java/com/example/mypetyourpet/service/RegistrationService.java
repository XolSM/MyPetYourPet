package com.example.mypetyourpet.service;

import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistrationService {
    private final PetOwnerUserRepository petOwnerUserRepository;
    private final PetSeekerUserRepository petSeekerUserRepository;

    public RegistrationService(PetOwnerUserRepository petOwnerUserRepository, PetSeekerUserRepository petSeekerUserRepository) {
        this.petOwnerUserRepository = petOwnerUserRepository;
        this.petSeekerUserRepository = petSeekerUserRepository;
    }
    // private final PetSeekerUserRepository petSeekerUserRepository; // later when I do the Seeker version


    public PetOwnerUser registerPetOwner(RegistrationRequest request) {

        if (petOwnerUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("A user with this email already exists");
        }

        PetOwnerUser owner = new PetOwnerUser();
        owner.createAccount(
                0L,  // id is auto-generated; we ignore uid here
                request.getFullName(),
                request.getEmail(),
                request.getProfilePic(),
                request.getAge() != null ? request.getAge() : 0,
                request.getGender(),
                request.getGovernmentId(),
                request.getLocation(),
                "PENDING VERIFICATION",              // profileStatus
                0.0,                   // ratingAvg
                new Date(),            // registerDate now
                "PetOwner"
        );

        return petOwnerUserRepository.save(owner);
    }

    // the seeker
    public PetSeekerUser registerPetSeeker(RegistrationRequest request) {
        if (petSeekerUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("A user with this email already exists");
            //probably need to create an exception class to handle things.
        }
        PetSeekerUser seeker = new PetSeekerUser();
        seeker.createAccount(
                0L,  // id is auto-generated; we will set it up when we send UID from frontend
                request.getFullName(),
                request.getEmail(),
                request.getProfilePic(),
                request.getAge() != null ? request.getAge() : 0,
                request.getGender(),
                request.getGovernmentId(),
                request.getLocation(),
                "PENDING VERIFICATION",              // profileStatus
                0.0,                   // ratingAvg
                new Date(),            // registerDate now
                "PetSeeker" //might have to set it up to retrieve from the request somehow
                // or maybe we don't need to add it to the request because we set it here and
                //the frontend calls the specific endpoint based on the role
        );
        return petSeekerUserRepository.save(seeker);
    }



}
