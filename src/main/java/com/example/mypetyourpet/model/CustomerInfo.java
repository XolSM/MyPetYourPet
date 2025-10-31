package com.example.mypetyourpet.model;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class CustomerInfo {

   // private String customerType; // PetOwner, PetSeeker

    private String governmentID;

    private int age;

    private String gender;

    private Date registerDate;

    private String location;

    private String profileStatus; // Pending Verification, Verified, Not Verified, Inactive

    private double ratingAvg; // 1-5 scale

    private String ratingComments;

    public CustomerInfo() {}

    public CustomerInfo(String governmentID, int age, String gender,
                        Date registerDate, String location, String profileStatus) {
        this.governmentID = governmentID;
        this.age = age;
        this.gender = gender;
        this.registerDate = registerDate;
        this.location = location;
        this.profileStatus = profileStatus;
    }

    public String getGovernmentID() {
        return governmentID;
    }

    public void setGovernmentID(String governmentID) {
        this.governmentID = governmentID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getRatingComments() {
        return ratingComments;
    }

    public void setRatingComments(String ratingComments) {
        this.ratingComments = ratingComments;
    }
}
