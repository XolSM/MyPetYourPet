package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetOwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerUserRepository extends JpaRepository<PetOwnerUser, Long> {
    boolean existsByEmail(String email); //asking to check if the users email exists in the database
}