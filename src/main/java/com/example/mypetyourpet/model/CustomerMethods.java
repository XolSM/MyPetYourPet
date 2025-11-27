package com.example.mypetyourpet.model;

import java.util.Date;

public interface CustomerMethods {
    public void createAccount(long uid, String firebaseUID, String fullName, String email, String phone,
                              int age, String gender, String govenmentId, String backgroundCheck, String location, String status,
                              double ratingAvg, Date registerDate, String customerType, String profilePicture, String profilePicturePublicId, String bio);
    public void viewProfile();
    public void deleteAccount(String email);
    public void openClaim();
    public void cancelPetReservation();
    public void updateUserProfile();

    public void setDeletedAt(Date deletedAt);

}
