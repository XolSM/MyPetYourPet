package com.example.mypetyourpet.model;

import java.util.Date;

public interface CustomerMethods {
    public void createAccount(long uid, String fullName, String email, String profilePic,
                              int age, String gender, String govenmentId, String location, String status,
                              double ratingAvg, Date registerDate, String customerType);
    public void viewProfile();
    public void deleteAccount();
    public void openClaim();
    public void cancelPetReservation();
    public void updateUserProfile();
}
