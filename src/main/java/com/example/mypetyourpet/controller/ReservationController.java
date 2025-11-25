package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.model.PetReservation;
import com.example.mypetyourpet.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService service;



    //A seeker makes a reservation
    @PostMapping("/seeker/create")
    public ResponseEntity<PetReservation> createReservation(@RequestBody PetReservation reservation) {
        return ResponseEntity.ok(service.createReservation(reservation));
    }

    //Seeker views their reservations
    @GetMapping("/seeker/{customerId}")
    public ResponseEntity<List<PetReservation>> getReservationsForSeeker(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getReservationsBySeeker(customerId));
    }

    //Pet Owner views reservations related to their pet
    @GetMapping("/owner/pet/{petId}")
    public ResponseEntity<List<PetReservation>> getReservationsForPet(@PathVariable Long petId) {
        return ResponseEntity.ok(service.getReservationsByPet(petId));
    }

}
