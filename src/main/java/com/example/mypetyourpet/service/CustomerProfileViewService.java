package com.example.mypetyourpet.service;

import com.example.mypetyourpet.dto.CustomerProfileViewResponse;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileViewService {
   // private final CustomerProfileViewService customerProfileViewService; //don't know if I need this
    private final PetOwnerUserRepository petOwnerUserRepository;
    private final PetSeekerUserRepository petSeekerUserRepository; //when I do the one for Seeker profile

    public CustomerProfileViewService(PetOwnerUserRepository petOwnerUserRepository, PetSeekerUserRepository petSeekerUserRepository) {
       this. petOwnerUserRepository = petOwnerUserRepository;
        this.petSeekerUserRepository = petSeekerUserRepository;
    }




    //we have different things to fetch for owner and user. If they are the same later, I'll merge these two methods into one later
    //Owner
    public CustomerProfileViewResponse viewOwnerProfile(String email) {
        PetOwnerUser owner = petOwnerUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User with email" + email + "does not exist"));

        CustomerProfileViewResponse dtoProfile = new CustomerProfileViewResponse(); //create a new profile dto for owner

        dtoProfile.setFullName(owner.getFullName());
        dtoProfile.setEmail(owner.getEmail());
        dtoProfile.setProfilePic(owner.getProfilePicture());
        dtoProfile.setRole(owner.getCustomerType());
        //dtoProfile.setGender(seeker.getGender());
        //dtoProfile.setPhoneNumber(seeker.getPhoneNumber());
        //dtoProfile.setBio(seeker.getBio);

        //embedded customer info
        if (owner.getCustomerInfo() != null) {
            dtoProfile.setLocation(owner.getCustomerInfo().getLocation());
            dtoProfile.setStatus(owner.getCustomerInfo().getProfileStatus());
            // dtoProfile.setProfileStatus(seeker.getCustomerInfo().getProfileStatus());
        }


        return dtoProfile;
    }

    //Seeker
    public CustomerProfileViewResponse viewSeekerProfile(String email) {
        PetSeekerUser seeker = petSeekerUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User with email" + email + "does not exist"));

        CustomerProfileViewResponse dtoProfile = new CustomerProfileViewResponse(); //create a new profile dto

        dtoProfile.setFullName(seeker.getFullName());
        dtoProfile.setEmail(seeker.getEmail());
        dtoProfile.setProfilePic(seeker.getProfilePicture());
        dtoProfile.setRole(seeker.getCustomerType());
        //dtoProfile.setGender(seeker.getGender());
        //dtoProfile.setPhoneNumber(seeker.getPhoneNumber());
        //dtoProfile.setBio(seeker.getBio);

        //embedded customer info
        if (seeker.getCustomerInfo() != null) {
            dtoProfile.setLocation(seeker.getCustomerInfo().getLocation());
            dtoProfile.setStatus(seeker.getCustomerInfo().getProfileStatus());
            // dtoProfile.setProfileStatus(seeker.getCustomerInfo().getProfileStatus());
        }


        return dtoProfile;
    }


}
