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
    public PetSeekerUser(Long id, String fullName, String email, CustomerInfo customerInfoString,
                         String profilePicture, String profilePicturePublicId) {
        super(id, fullName, email, profilePicture, profilePicturePublicId);
        this.customerInfo = customerInfo;
    }
    public
    PetSeekerUser(Long id, String fullName, String email, String phone, String governmentID, String backgroundCheck,
                         int age, String gender, Date registerDate, String location,
                         String profileStatus, String profilePicture, String profilePicturePublicId, String bio) {
        super(id, fullName, email, profilePicture, profilePicturePublicId);
        this.customerInfo = new CustomerInfo(phone, governmentID, backgroundCheck,age, gender,
                registerDate, location, profileStatus,bio);


    }

    public void createAccount(long uid, String firebaseUID, String fullName, String email, String phone,
                              int age, String gender, String governmentId, String backgroundCheck, String location, String status,
                              double ratingAvg, Date registerDate,String bio, String customerType, String profilePicture, String profilePicturePublicId) {
        // store the information in teh database

        this.setFullName(fullName); //calling the methods in the User class
        this.setEmail(email);
        this.setFirebaseUID(firebaseUID);
        //creating a customer info instance with the data
        this.customerInfo = new CustomerInfo(
                phone, governmentId, backgroundCheck, age, gender, registerDate, location, status,bio
                //I may have to add ratings and comments and set it as null and 0 when the customer is first
                //created
        );
        this.customerType = customerType != null ? customerType : "PetSeeker"; //condition ? valueIfTrue : valueIfFalse

        // we do not talk directly with the database here. we do that in repository
        this.profilePicturePublicId = profilePicturePublicId;
        this.profilePicture = profilePicture;
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
    public void setDeletedAt(Date deletedAt) {

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
