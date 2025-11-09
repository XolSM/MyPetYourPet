package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetSeekerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetSeekerUserRepository extends JpaRepository<PetSeekerUser, Long> {
    boolean existsByEmail(String email);
}
