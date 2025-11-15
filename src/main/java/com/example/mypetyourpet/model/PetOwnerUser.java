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
    public PetOwnerUser(Long id, String fullName, String email, CustomerInfo customerInfo,
                        String profilePicture, String profilePicturePublicId) {
        super(id, fullName, email, profilePicture, profilePicturePublicId);
        this.customerInfo = customerInfo;
    }
    public PetOwnerUser(long id, String fullName, String email, String phone, String governmentID,
                        int age, String gender, Date registerDate, String location,
                        String profileStatus, String profilePicture, String profilePicturePublicId) {
        super(id, fullName, email, profilePicture, profilePicturePublicId);
        this.customerInfo = new CustomerInfo(phone, governmentID, age, gender,
                registerDate, location, profileStatus);
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
    //public void setCustomerInfo(CustomerInfo customerInfo) {}

    public String getCustomerType() {return customerType;}

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
    public void createAccount(long uid, String firebaseUID, String fullName, String email, String phone,
                              int age, String gender, String governmentId, String location, String status,
                              double ratingAvg, Date registerDate, String customerType, String profilePicture, String profilePicturePublicId) {
        // store the information in teh database

        this.setFullName(fullName); //calling the methods in the User class
        this.setEmail(email);
        this.setFirebaseUID(firebaseUID);
        //creating a customer info instance with the data
        this.customerInfo = new CustomerInfo(
                phone, governmentId, age, gender, registerDate, location, status
                //I may have to add ratings and comments and set it as null and 0 when the customer is first
                //created
        );
        this.customerType = customerType != null ? customerType : "PetOwner"; //condition ? valueIfTrue : valueIfFalse
                                                                             //java ternary operator
        // we do not talk directly with the database here. we do that in repository
        this.profilePicturePublicId = profilePicturePublicId;
        this.profilePicture = profilePicture;
    }

    @Override
    public void viewProfile() {}

    @Override
    public void deleteAccount(String email) {

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
