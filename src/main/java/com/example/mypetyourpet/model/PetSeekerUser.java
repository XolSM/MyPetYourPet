package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class PetSeekerUser extends User implements PetSeeker, CustomerMethods{
    protected CustomerInfo customerInfo;

    public PetSeekerUser() {}
    public PetSeekerUser(Long id, String fullName, String email, CustomerInfo customerInfo) {
        super(id,fullName, email);
        this.customerInfo = customerInfo;
    }
    public PetSeekerUser(Long id, String fullName, String email, String governmentID,
                         int age, String gender, Date registerDate, String location,
                         String profileStatus) {
        super(id,fullName, email);
        this.customerInfo = new CustomerInfo(governmentID, age, gender,
                registerDate, location, profileStatus);


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

    @Override
    public void searchPetListings() {

    }

    @Override
    public void viewPetProfile() {

    }

    @Override
    public void requestPetReservation() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void ratePetOwner() {

    }
}
