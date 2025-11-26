package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetSeekerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetSeekerUserRepository extends JpaRepository<PetSeekerUser, Long> {
    boolean existsByEmail(String email);

    // to fetch seeker info
    Optional<PetSeekerUser> findByEmail(String email);

    void deleteByEmail(String email); //delete account

    //boolean existsByFirebaseUID(String firebaseUID);


}
