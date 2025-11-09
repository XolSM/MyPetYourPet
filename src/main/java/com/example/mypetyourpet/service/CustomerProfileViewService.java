package com.example.mypetyourpet.service;

import com.example.mypetyourpet.dto.CustomerProfileViewRequest;
import com.example.mypetyourpet.dto.RegistrationRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerProfileViewService {
   // private final CustomerProfileViewService customerProfileViewService; //dont know if I need this
    private final PetOwnerUserRepository petOwnerUserRepository;
    //private final PetSeekerUserRepository petSeekerUserRepository; //when I do the one for Seeker profile

    public CustomerProfileViewService(PetOwnerUserRepository petOwnerUserRepository, PetSeekerUserRepository petSeekerUserRepository) {
       this. petOwnerUserRepository = petOwnerUserRepository;
    }

    public PetOwnerUser viewProfile(CustomerProfileViewRequest request) {

        if (!petOwnerUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User does not exist"); // But if they are already logged in they should be able to fetch their data??
        }

        //if it exists --> we will get their details


        PetOwnerUser owner = new PetOwnerUser();
        owner.viewProfile(
//                0L,  // id is auto-generated; we ignore uid here
//                request.setFullName(),
//                request.setEmail(),
//                request.getProfilePic(),
//                request.getAge() != null ? request.getAge() : 0,
//                request.getGender(),
//                request.getGovernmentId(),
//                request.getLocation(),
//                "PENDING VERIFICATION",              // profileStatus
//                0.0,                   // ratingAvg
//                new Date(),            // registerDate now
//                "PetOwner"
        );

        return petOwnerUserRepository.save(owner);
    }
}
