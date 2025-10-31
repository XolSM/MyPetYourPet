package com.example.mypetyourpet.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class PetOwnerUser extends User implements PetOwner, CustomerMethods {

    @Embedded
    protected CustomerInfo customerInfo;

    @Column(name="customerType")
    private String customerType = "PetOwner";

    public PetOwnerUser() {}
    public PetOwnerUser(Long id, String fullName, String email, CustomerInfo customerInfo) {
        super(id,fullName, email);
        this.customerInfo = customerInfo;
    }
    public PetOwnerUser(Long id, String fullName, String email, String governmentID,
                        int age, String gender, Date registerDate, String location,
                        String profileStatus) {
        super(id,fullName, email);
        this.customerInfo = new CustomerInfo(governmentID, age, gender,
                registerDate, location, profileStatus);


    }

    @Override
    public void createPetProfile() {

    }

    @Override
    public void viewPetProfile() {

    }

    @Override
    public void viewPetReservation() {

    }

    @Override
    public void ratePetSeeker() {

    }

    @Override
    public void createAccount() {

    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void openClaim() {

    }

    @Override
    public void cancelPetReservation() {

    }

    @Override
    public void updateUserProfile() {

    }
}
