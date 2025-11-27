package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.Administrator;
import com.example.mypetyourpet.model.AdministratorUser;
import com.example.mypetyourpet.model.PetOwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupportUserRepository extends JpaRepository<AdministratorUser, Integer> {

    Optional<AdministratorUser> findByFirebaseUID(String firebaseUID);

    boolean existsByFirebaseUID(String firebaseUID) ;

    Optional<Administrator> findById(Long id);
}
