package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.CustomerProfileViewResponse;
import com.example.mypetyourpet.service.CustomerProfileViewService;
import com.example.mypetyourpet.service.DeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/customerAccount")
public class CustomerProfileViewController {

    private final CustomerProfileViewService customerProfileViewService; //to view profile
    private final DeleteService deleteService; //to delete an account

    public CustomerProfileViewController(CustomerProfileViewService customerProfileViewService, DeleteService deleteService) {
        this.customerProfileViewService = customerProfileViewService;
        this.deleteService = deleteService;
    }


    //we will change all the request params to accept token and verify first


    @GetMapping("/petSeekerProfile")
    public ResponseEntity<CustomerProfileViewResponse> viewSeekerProfile(@RequestParam String email) {
        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewSeekerProfile(email);
        return ResponseEntity.ok(dtoProfile);
    }

    @GetMapping("/petOwnerProfile")
    public ResponseEntity<CustomerProfileViewResponse> viewOwnerProfile(@RequestParam String email) {
        CustomerProfileViewResponse dtoProfile = customerProfileViewService.viewOwnerProfile(email);
        return ResponseEntity.ok(dtoProfile);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deletePetSeekerProfile(@RequestParam String email) {

        deleteService.delete(email);
        return ResponseEntity.ok("Account deleted Successfully");
    }
}
