package com.example.mypetyourpet.service;

import com.example.mypetyourpet.model.PetReservation;
import com.example.mypetyourpet.repository.PetReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final PetReservationRepository repository;

    public ReservationService(PetReservationRepository repository) {
        this.repository = repository;
    }

    // For Pet Seeker (making a reservation)
    public PetReservation createReservation(PetReservation reservation) {
        reservation.setDateCreated(new Date());
        reservation.setPetResStatus("Pending");
        return repository.save(reservation);
    }

    // For Pet Seeker - view their reservations
    public List<PetReservation> getReservationsBySeeker(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    // For Pet Owner - view reservations on their pets
    public List<PetReservation> getReservationsByPet(Long petId) {
        return repository.findByPetId(petId);
    }
}
