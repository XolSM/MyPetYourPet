package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PetReservationRepository extends JpaRepository<PetReservation, Long> {
    List<PetReservation> findByCustomerId(Long customerId);
    List<PetReservation> findByPetId(Long petId);
    @Query("SELECT r FROM PetReservation r JOIN Pet p ON r.petId = p.petId WHERE p.customerId = :ownerId")
    List<PetReservation> findReservationsForOwner(Long ownerId);

    boolean existsByPetIdAndStartDateLessThanAndEndDateGreaterThan(
            Long petId,
            Date endDate,
            Date startDate
    );

}