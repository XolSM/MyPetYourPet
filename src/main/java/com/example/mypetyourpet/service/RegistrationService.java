package com.example.mypetyourpet.service;

import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final PetOwnerUserRepository petOwnerUserRepository;
    private final PetSeekerUserRepository petSeekerUserRepository;

    public PetOwnerUser registerPetOwner(RegistrationRequest request) {

        if (petOwnerUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("A user with this email already exists");
        }

        PetOwnerUser owner = new PetOwnerUser();
        owner.createAccount(
                0L,  // id is auto-generated; we ignore uid here
                request.getFirebaseUID(),
                request.getFullName(),
                request.getEmail(),
                request.getPhone(),
                request.getAge() != null ? request.getAge() : 0, //WE SHOULD VERIFY IF LESS THAN 18
                request.getGender(),
                request.getGovernmentId(),
                request.getBackgroundCheck(),
                request.getLocation(),
                "PENDING VERIFICATION",              // profileStatus
                0.0,                   // ratingAvg
                new Date(),
                request.getBio(),// registerDate now
                "PetOwner",
                request.getProfilePic(),
                request.getProfilePicturePublicId()
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
                request.getFirebaseUID(),
                request.getFullName(),
                request.getEmail(),
                request.getPhone(),
                request.getAge() != null ? request.getAge() : 0,
                request.getGender(),
                request.getGovernmentId(),
                request.getBackgroundCheck(),
                request.getLocation(),
                "PENDING VERIFICATION",              // profileStatus
                0.0,                   // ratingAvg
                new Date(), // registerDate now
                request.getBio(),
                "PetSeeker",
                request.getProfilePic(),
                request.getProfilePicturePublicId()
        );
        return petSeekerUserRepository.save(seeker);
    }



}
