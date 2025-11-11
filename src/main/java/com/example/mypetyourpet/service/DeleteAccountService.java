package com.example.mypetyourpet.service;

import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteAccountService {

    private final PetSeekerUserRepository petSeekerUserRepository;
    private final PetOwnerUserRepository petOwnerUserRepository;

    public DeleteAccountService(PetSeekerUserRepository petSeekerUserRepository, PetOwnerUserRepository petOwnerUserRepository) {
        this.petSeekerUserRepository = petSeekerUserRepository;
        this.petOwnerUserRepository = petOwnerUserRepository;
    }

    @Transactional
    public void  delete(String email) {
        boolean deleted = false;

        if (petSeekerUserRepository.existsByEmail(email)) {
            petSeekerUserRepository.deleteByEmail(email);
            deleted = true;
        }

        if (petOwnerUserRepository.existsByEmail(email)) {
            petOwnerUserRepository.deleteByEmail(email);
            deleted = true;
        }

        if (!deleted) {
            throw new IllegalStateException("Account with email " + email + " not found");
        }

    }
}
