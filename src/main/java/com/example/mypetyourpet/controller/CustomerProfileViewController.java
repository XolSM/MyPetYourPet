package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.CustomerProfileViewResponse;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.repository.PetOwnerUserRepository;
import com.example.mypetyourpet.service.CustomerProfileViewService;
import com.example.mypetyourpet.service.DeleteAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/customerAccount")
@AllArgsConstructor
public class CustomerProfileViewController {

    private final CustomerProfileViewService customerProfileViewService; //to view profile
    private final DeleteAccountService deleteAccountService; //to delete an account
    private final PetOwnerUserRepository petOwnerUserRepository;

//    public CustomerProfileViewController(CustomerProfileViewService customerProfileViewService, DeleteAccountService deleteAccountService) {
//        this.customerProfileViewService = customerProfileViewService;
//        this.deleteAccountService = deleteAccountService;
//    }


    //we will change all the request params to accept token and verify first


    @GetMapping("/petSeekerProfile")
    public ResponseEntity<CustomerProfileViewResponse> viewSeekerProfile(@RequestParam String email) {
        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewSeekerProfile(email);
        return ResponseEntity.ok(dtoProfile);
    }

    @GetMapping("/getPetOwnerdetails/{id}")
    public ResponseEntity<PetOwnerUser> getCustomerById(@PathVariable Long id) {
        Optional<PetOwnerUser> customer = petOwnerUserRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
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
