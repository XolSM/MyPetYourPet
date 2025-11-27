package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.CustomerDTO;
import com.example.mypetyourpet.dto.VerifyRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final PetOwnerUserRepository ownerRepo;
    private final PetSeekerUserRepository seekerRepo;

    // 🔹 Fetch pending users
    @GetMapping("/pending-verifications")
    public List<CustomerDTO> getPendingUsers() {
        List<CustomerDTO> result = new ArrayList<>();

        ownerRepo.findByCustomerInfo_ProfileStatusIgnoreCase("pending verification")
                .forEach(u -> result.add(CustomerDTO.fromUser(u)));

//        seekerRepo.findByCustomerInfo_ProfileStatusIgnoreCase("pending verification")
//                .forEach(u -> result.add(CustomerDTO.fromUser(u)));

        return result;
    }

    // 🔹 Count verified accounts
    @GetMapping("/verified-count")
    public Long getVerifiedCount() {
        return ownerRepo.countByCustomerInfo_ProfileStatusIgnoreCase("verified");
//                seekerRepo.countByCustomerInfo_ProfileStatusIgnoreCase("verified");
    }

    // 🔹 Approve or reject
    @PostMapping("/verify-user/{id}")
    public ResponseEntity<?> verifyUser(@PathVariable Long id, @RequestBody VerifyRequest req) {
        String newStatus = req.getAction().equals("approve") ? "VERIFIED" : "REJECTED";

        PetOwnerUser owner = ownerRepo.findById(id).orElse(null);
        if (owner != null) {
            owner.getCustomerInfo().setProfileStatus(newStatus);
            ownerRepo.save(owner);
            return ResponseEntity.ok("Updated (Owner)");
        }

        PetSeekerUser seeker = seekerRepo.findById(id).orElse(null);
        if (seeker != null) {
            seeker.getCustomerInfo().setProfileStatus(newStatus);
            seekerRepo.save(seeker);
            return ResponseEntity.ok("Updated (Seeker)");
        }

        return ResponseEntity.status(404).body("User not found");
    }
}
