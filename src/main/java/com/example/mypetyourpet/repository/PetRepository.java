package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Long> {
    Optional<Pet> findPetByPetNameAndCustomerId(String petName, Long customerId);
//    Pet findPetByPetNameAndCustomerId(String petName, Long customerId);
    Optional<Pet> findPetByCustomerId(Long customerId);
//    Pet findPetByCustomerId(Long customerId);
//    Optional<List<Pet>> findByPetProfileStatus(PetProfileStatus petProfileStatus);

    List<Pet> findPetListByPetNameAndCustomerId(String petName, Long customerId);
    Optional<List<Pet>> findPetListByCustomerId(Long customerId);
    List<Pet> findPetListByPetProfileStatus(PetProfileStatus petProfileStatus);
}
