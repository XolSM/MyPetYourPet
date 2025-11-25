package com.example.mypetyourpet.service;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DeleteAccountService {

    private final PetSeekerUserRepository petSeekerUserRepository;
    private final PetOwnerUserRepository petOwnerUserRepository;
    private final PetRepository petRepository;

    public DeleteAccountService(PetSeekerUserRepository petSeekerUserRepository, PetOwnerUserRepository petOwnerUserRepository, PetRepository petRepository) {
        this.petSeekerUserRepository = petSeekerUserRepository;
        this.petOwnerUserRepository = petOwnerUserRepository;
        this.petRepository = petRepository;
    }

//    @Transactional
//    public void  delete(String email) {
//        boolean deleted = false;
//
//        if (petSeekerUserRepository.existsByEmail(email)) {
//            petSeekerUserRepository.deleteByEmail(email);
//            deleted = true;
//        }
//
//        if (petOwnerUserRepository.existsByEmail(email)) {
//            petOwnerUserRepository.deleteByEmail(email);
//            deleted = true;
//        }
//
//        if (!deleted) {
//            throw new IllegalStateException("Account with email " + email + " not found");
//        }
//
//    }

    @Transactional
    public void deleteSeeker(String email) {

        PetSeekerUser seeker = petSeekerUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Seeker not found"));

        // soft delete

        seeker.getCustomerInfo().setProfileStatus("DELETED");
        seeker.setDeletedAt(new Date());
        seeker.setFullName("Deleted User");
        seeker.setEmail("deleted_" + seeker.getId() + "@mpyp.invalid");
        seeker.getCustomerInfo().setPhone(null);
        seeker.setProfilePicture("src/main/resources/profile.png");

        petSeekerUserRepository.save(seeker);
    }

    @Transactional
    public void deleteOwner(String email) {

        PetOwnerUser owner = petOwnerUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Owner not found"));

        // Soft delete owner
        owner.getCustomerInfo().setProfileStatus("DELETED");
        owner.setDeletedAt(new Date());
        owner.setEmail("deleted_" + owner.getId() + "@mpyp.invalid");
        owner.setFullName("Deleted User");
        owner.getCustomerInfo().setPhone(null);
        owner.setProfilePicture("src/main/resources/profile.png");

        // Soft delete pets
        List<Pet> pets = petRepository.findPetsByCustomerId(owner.getId());
        for (Pet pet : pets) {
            pet.setPetProfileStatus(false);        // hide pet
            pet.setDeletedAt(new Date());          // timestamp
            pet.setPetName("Deleted Pet");                  // anonymize
            pet.setPetBreed("");                 // anonymize
            pet.setProfilePictureUrl("src/main/resources/pet.png");
        }

        petOwnerUserRepository.save(owner);
        petRepository.saveAll(pets);
    }
}
