package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.model.PetReservation;
import com.example.mypetyourpet.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService service;



    //A seeker makes a reservation
    @PostMapping("/seeker/create")
    public ResponseEntity<PetReservation> createReservation(@RequestBody PetReservation reservation) {
        return ResponseEntity.ok(service.createReservation(reservation));
    }

    //Seeker views their reservations
    //@GetMapping("/seeker/{customerId}")
    @GetMapping("/seeker/view/{customerId}")
    public ResponseEntity<List<PetReservation>> getReservationsForSeeker(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getReservationsBySeeker(customerId));
    }

    //Pet Owner views reservations related to their pet
    @GetMapping("/owner/pet/{petId}")
    public ResponseEntity<List<PetReservation>> getReservationsForPet(@PathVariable Long petId) {
        return ResponseEntity.ok(service.getReservationsByPet(petId));
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<PetReservation> updateStatus(
            @PathVariable int id,
            @RequestBody Map<String, String> request
    ) {
        return ResponseEntity.ok(service.updateReservationStatus(id, request.get("status")));
    }

    @GetMapping("/owner/view/{ownerId}")
    public ResponseEntity<List<PetReservation>> getReservationsForOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(service.getReservationsForOwner(ownerId));
    }

    @GetMapping("/check-availability")
    public ResponseEntity<Map<String, Boolean>> checkAvailability(
            @RequestParam Long petId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate
    ) {
        boolean available = service.isPetAvailable(petId, startDate, endDate);
        return ResponseEntity.ok(Collections.singletonMap("available", available));
    }

}
