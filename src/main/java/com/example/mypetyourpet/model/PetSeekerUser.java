package com.example.mypetyourpet.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class PetSeekerUser extends User implements PetSeeker, CustomerMethods{

    @Embedded
    protected CustomerInfo customerInfo;

    @Column(name="customerType")
    private String customerType = "PetSeeker";

    public PetSeekerUser() {}
    public PetSeekerUser(Long id, String fullName, String email, CustomerInfo customerInfo) {
        super(fullName, email);
        this.customerInfo = customerInfo;
    }
    public
    PetSeekerUser(Long id, String fullName, String email, String governmentID,
                         int age, String gender, Date registerDate, String location,
                         String profileStatus) {
        super(fullName, email);
        this.customerInfo = new CustomerInfo(governmentID, age, gender,
                registerDate, location, profileStatus);


    }

    @Override
    public void createAccount(long uid, String fullName, String email, String profilePic,
                              int age, String gender, String governmentId, String location, String status,
                              double ratingAvg, Date registerDate, String customerType) {

        this.setFullName(fullName); //calling the methods in the User class
        this.setEmail(email);

        //creating a customer info instance with the data
        this.customerInfo = new CustomerInfo(
                governmentId, age, gender, registerDate, location, status
                //I may have to add ratings and comments and set it as null and 0 when the customer is first
                //created
        );
        this.customerType = customerType != null ? customerType : "PetSeeker";
        //Need to create enums later for status

    }

    public CustomerInfo getCustomerInfo() {return customerInfo;}
    public String getCustomerType() {return customerType;}

    @Override
    public void viewProfile() {
        /*
        I need the profile controller
        profile service
        Both Petowner and petseeker repositories
        dto to define what is actually getting sent (Profile response)
         */
    }

    @Override
    public void deleteAccount(String email) {

        //Using one service to delete both accounts.

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
