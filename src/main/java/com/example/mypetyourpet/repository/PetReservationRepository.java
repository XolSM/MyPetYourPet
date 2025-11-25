package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetReservationRepository extends JpaRepository<PetReservation, Long> {
    List<PetReservation> findByCustomerId(Long customerId);
    List<PetReservation> findByPetId(Long petId);
}