package com.example.mypetyourpet.service;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetReservation;
import com.example.mypetyourpet.repository.PetRepository;
import com.example.mypetyourpet.repository.PetReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final PetReservationRepository petReservationRepository;
    @Autowired
    private PetRepository petRepository;
    public ReservationService(PetReservationRepository repository, PetRepository petRepository) {
        this.petReservationRepository = repository;
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
        return petReservationRepository.save(reservation);
    }

    public List<PetReservation> getReservationsBySeeker(Long customerId) {
        List<PetReservation> reservations = petReservationRepository.findByCustomerId(customerId);

        for (PetReservation res : reservations) {
            Pet pet = petRepository.findById(res.getPetId()).orElse(null);
            if (pet != null) {
                res.setPetName(pet.getPetName());  // Adding pet name dynamically to populate reservation pet name
                res.setPetImageUrl(pet.getProfilePicture());
            }
        }
        return reservations;
    }

    // For Pet Owner - view reservations on their pets
    public List<PetReservation> getReservationsByPet(Long petId) {
        return petReservationRepository.findByPetId(petId);
    }

    public PetReservation updateReservationStatus(int id, String status) {
        List<String> allowedStatuses = List.of(
                "Pending",
                "Rejected",
                "Confirmed - Pending Payment",
                "Confirmed - Paid"
        );

        if (!allowedStatuses.contains(status)) {
            throw new IllegalArgumentException("Invalid reservation status");
        }

        PetReservation reservation = petReservationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setPetResStatus(status);
        return petReservationRepository.save(reservation);
    }
    public List<PetReservation> getReservationsForOwner(Long ownerId) {
        List<PetReservation> reservations = petReservationRepository.findReservationsForOwner(ownerId);

        for (PetReservation res : reservations) {
            Pet pet = petRepository.findById(res.getPetId()).orElse(null);
            if (pet != null) {
                res.setPetName(pet.getPetName());
                res.setPetImageUrl(pet.getProfilePicture());
            }
        }
        return reservations;
    }

    //find customerId to create transaction
    public int getCustomerId(int reservationId) {
        PetReservation r = petReservationRepository.findById((long) reservationId)
                .orElseThrow(() -> new IllegalStateException("Reservation not found"));

        return Math.toIntExact(r.getCustomerId());

    }

    public boolean isPetAvailable(Long petId, Date startDate, Date endDate) {

        boolean hasConflict =
                petReservationRepository.existsByPetIdAndStartDateLessThanAndEndDateGreaterThan(
                        petId,
                        endDate,
                        startDate
                );

        return !hasConflict; // available if NO conflicts
    }
}
