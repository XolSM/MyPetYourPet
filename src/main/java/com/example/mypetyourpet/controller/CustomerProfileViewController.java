package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.CustomerProfileViewResponse;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.User;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.repository.PetSeekerUserRepository;
import com.example.mypetyourpet.service.CustomerProfileViewService;
import com.example.mypetyourpet.service.DeleteAccountService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/customerAccount")
@AllArgsConstructor
public class CustomerProfileViewController {

    private final CustomerProfileViewService customerProfileViewService; //to view profile
    private final DeleteAccountService deleteAccountService; //to delete an account
    private final PetOwnerUserRepository petOwnerUserRepository;
    private final PetSeekerUserRepository petSeekerUserRepository;

//    public CustomerProfileViewController(CustomerProfileViewService customerProfileViewService, DeleteAccountService deleteAccountService) {
//        this.customerProfileViewService = customerProfileViewService;
//        this.deleteAccountService = deleteAccountService;
//    }


    //we will change all the request params to accept token and verify first



    //I used this to test on only one controller method. but i added the security configuration
    // and all the other methods might require a header
    @Autowired
    private FirebaseAuth firebaseAuth;

    @GetMapping("/userRole")
    public ResponseEntity<?> getUserRole(@RequestHeader("Authorization") String authHeader)
    {

        try {
            String token = authHeader.replace("Bearer ", "").trim();
            FirebaseToken decoded = firebaseAuth.verifyIdToken(token);

            String uid = decoded.getUid();
            String email = decoded.getEmail();
            //Long id ;

            // Figure out if this UID is an owner or seeker
            String customerType = null;
            String role = null;
            Long id;

            if (petOwnerUserRepository.existsByFirebaseUID(uid)) //since they both use one table
            {
                customerType = (petOwnerUserRepository.findByFirebaseUID(uid)).get().getCustomerType();
                User user = petOwnerUserRepository.findByFirebaseUID(uid).get();
                 id = user.getId();

                if (customerType.equals("PetOwner") )
                {
                    role = "owner";
                }
                else
                {
                    role = "seeker";
                }
               //retreive id here and send to frontend to keep track of who is logged in

            } else {
                // no record in either table
                return ResponseEntity.status(404)
                        .body(Map.of(
                                "message", "No user found for this UID",
                                "uid", uid
                        ));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("uid", uid);
            response.put("email", email);
            response.put("role", role);
            response.put("valid", true);
            response.put("id", id); //check on this

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }


    }

//    @GetMapping("/userRole")
//    public ResponseEntity<?> getUserRole(@RequestHeader("Authorization") String authHeader) {
//
//        try {
//            System.out.println("AUTH HEADER: [" + authHeader + "]");
//
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                System.out.println("No Bearer token found in Authorization header");
//                return ResponseEntity.status(401).body("Missing or invalid Authorization header");
//            }
//
//            // safer than .replace()
//            String token = authHeader.substring(7).trim(); // after "Bearer "
//            System.out.println("RAW TOKEN: [" + token + "]");
//            System.out.println("TOKEN LENGTH: " + token.length());
//
//            FirebaseToken decoded = firebaseAuth.verifyIdToken(token);
//
//            String uid = decoded.getUid();
//
//            String role;
//            if (petOwnerUserRepository.existsByFirebaseUID(uid)) {
//                role = "owner";
//            } else if (petSeekerUserRepository.existsByFirebaseUID(uid)) {
//                role = "seeker";
//            } else {
//                return ResponseEntity.status(404).body(
//                        Map.of("message", "No user found for this UID")
//                );
//            }
//
//            return ResponseEntity.ok(Map.of("role", role));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(401)
//                    .body("Invalid or expired token");
//        }
//    }





    @GetMapping("/petSeekerProfile")
    public ResponseEntity<CustomerProfileViewResponse> viewSeekerProfile(@RequestParam String email) {
        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewSeekerProfile(email);
        return ResponseEntity.ok(dtoProfile);
    }

    @GetMapping("/getCustomerDetails/{id}")
    public ResponseEntity<PetOwnerUser> getCustomerById(@PathVariable Long id) {
        Optional<PetOwnerUser> customer = petOwnerUserRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping("/getPetOwnerdetails/{id}")
//    public ResponseEntity<PetOwnerUser> getCustomerById(@PathVariable Long id) {
//        Optional<PetOwnerUser> customer = petOwnerUserRepository.findById(id);
//        return customer.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
    //    @GetMapping("/getPetOwnerdetails/{id}")
//    public ResponseEntity<CustomerProfileViewResponse> viewOwnerProfile(@RequestParam String email) {
//        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewOwnerProfile(email);
//        return ResponseEntity.ok(dtoProfile);
//    }

    @GetMapping("/petOwnerProfile")
    public ResponseEntity<CustomerProfileViewResponse> viewOwnerProfile(@RequestParam String email) {
        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewOwnerProfile(email);
        return ResponseEntity.ok(dtoProfile);
    }



    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deletePetSeekerProfile(@RequestParam String email) {

        deleteAccountService.delete(email);
        return ResponseEntity.ok("Account deleted Successfully");
    }
}
