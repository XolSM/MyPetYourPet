package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.CustomerDTO;
import com.example.mypetyourpet.dto.VerifyRequest;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.model.AdministratorUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import com.example.mypetyourpet.repository.SupportUserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    private final PetOwnerUserRepository ownerRepo;
    private final PetSeekerUserRepository seekerRepo;
    private final SupportUserRepository supportUserRepository;  //  inject this

    @GetMapping("/pending-verifications")
    public List<CustomerDTO> getPendingUsers() {
        List<CustomerDTO> result = new ArrayList<>();
        ownerRepo.findByCustomerInfo_ProfileStatusIgnoreCase("pending verification")
                .forEach(u -> result.add(CustomerDTO.fromUser(u)));
        return result;
    }

    @GetMapping("/verified-count")
    public Long getVerifiedCount() {
        return ownerRepo.countByCustomerInfo_ProfileStatusIgnoreCase("verified");
    }

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

    @GetMapping("/details")
    public ResponseEntity<?> getAdminDetails(@RequestHeader("Authorization") String authHeader) {
        // Firebase authentication stores UID as principal

        try{


        String token = authHeader.replace("Bearer ", "").trim();
        FirebaseToken decoded = firebaseAuth.verifyIdToken(token);

        String uid = decoded.getUid();
        //String email = decoded.getEmail();

        AdministratorUser admin = supportUserRepository
                .findByFirebaseUID(uid)
                .orElse(null);

        if (admin == null) {
            return ResponseEntity.status(404).body("Admin not found");
        }

        return ResponseEntity.ok(admin);
        }
        catch (Exception e ) {
            e.printStackTrace();
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }
    }

}
