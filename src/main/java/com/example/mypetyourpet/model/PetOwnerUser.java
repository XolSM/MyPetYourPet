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
    public void createAccount(long uid, String fullName, String email, String profilePic,
                              int age, String gender, String governmentId, String location, String status,
                              double ratingAvg, Date registerDate, String customerType) {
        // store the information in teh database

        this.setFullName(fullName); //calling the methods in the User class
        this.setEmail(email);

        //creating a customer info instance with the data
        this.customerInfo = new CustomerInfo(
                governmentId, age, gender, registerDate, location, status
                //I may have to add ratings and comments and set it as null and 0 when the customer is first
                //created
        );
        this.customerType = customerType != null ? customerType : "PetOwner"; //condition ? valueIfTrue : valueIfFalse
                                                                             //java ternary operator
        // we do not talk directly with the database here. we do that in repository
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
