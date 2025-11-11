package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetOwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetOwnerUserRepository extends JpaRepository<PetOwnerUser, Long> {
    boolean existsByEmail(String email);//asking to check if the users email exists in the database

    Optional<PetOwnerUser> findByEmail(String email); //CHANGE TO ID

    void deleteByEmail(String email); //delete account
}