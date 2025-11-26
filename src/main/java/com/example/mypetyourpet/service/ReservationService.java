package com.example.mypetyourpet.service;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetReservation;
import com.example.mypetyourpet.repository.PetRepository;
import com.example.mypetyourpet.repository.PetReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final PetReservationRepository repository;
    @Autowired
    private PetRepository petRepository;
    public ReservationService(PetReservationRepository repository, PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
    }

    // For Pet Seeker (making a reservation)
    public PetReservation createReservation(PetReservation reservation) {
        if (reservation.getPetId() == null || reservation.getCustomerId() == null) {
            throw new IllegalArgumentException("Required fields missing");
        }
        reservation.setDateCreated(new Date());
        if (reservation.getPetResStatus() == null) {
            reservation.setPetResStatus("Pending");
        }
        return repository.save(reservation);
    }

    // For Pet Seeker - view their reservations
    /*public List<PetReservation> getReservationsBySeeker(Long customerId) {
        return repository.findByCustomerId(customerId);
    }*/
    public List<PetReservation> getReservationsBySeeker(Long customerId) {
        List<PetReservation> reservations = repository.findByCustomerId(customerId);

        for (PetReservation res : reservations) {
            Pet pet = petRepository.findById(res.getPetId()).orElse(null);
            if (pet != null) {
                res.setPetName(pet.getPetName());  // Adding pet name dynamically to populate resevation pet name
                res.setPetImageUrl(pet.getProfilePicture());
            }
        }
        return reservations;
    }

    // For Pet Owner - view reservations on their pets
    public List<PetReservation> getReservationsByPet(Long petId) {
        return repository.findByPetId(petId);
    }

    public PetReservation updateReservationStatus(Long id, String status) {
        PetReservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setPetResStatus(status);
        return repository.save(reservation);
    }
    public List<PetReservation> getReservationsForOwner(Long ownerId) {
        List<PetReservation> reservations = repository.findReservationsForOwner(ownerId);

        for (PetReservation res : reservations) {
            Pet pet = petRepository.findById(res.getPetId()).orElse(null);
            if (pet != null) {
                res.setPetName(pet.getPetName());
                res.setPetImageUrl(pet.getProfilePicture());
            }
        }
        return reservations;
    }

}
