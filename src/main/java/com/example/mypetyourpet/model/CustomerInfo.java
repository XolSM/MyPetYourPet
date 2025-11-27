package com.example.mypetyourpet.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
public class CustomerInfo{

   // private String customerType; // PetOwner, PetSeeker

    @Column(name = "governmentid")
    private String governmentID; //url

    @Column(name = "backgroundCheck")
    private String backgroundCheck; //url

    private int age;

    private String gender;
    private String phone;

    private String bio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date")
    private Date registerDate;

    private String location;

    @Column(name = "profile_status")
    private String profileStatus; // Pending Verification, Verified, Not Verified, Inactive

    @Column(name = "rating_avg")
    private double ratingAvg; // 1-5 scale

    @Column(name = "rating_comments")
    private String ratingComments;


    private LocalDateTime deletedAt;

    public CustomerInfo() {}

    public CustomerInfo(String phone, String governmentID, String backgroundCheck, int age, String gender,
                        Date registerDate, String location, String profileStatus,String bio) {
        this.phone = phone;
        this.governmentID = governmentID;
        this.backgroundCheck = backgroundCheck;
        this.age = age;
        this.gender = gender;
        this.registerDate = registerDate;
        this.location = location;
        this.profileStatus = profileStatus;
        this.bio = bio;
    }

    public LocalDateTime getDeletedAt() {return deletedAt;}

    public void setDeletedAt(LocalDateTime deletedAt) {this.deletedAt = deletedAt;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGovernmentID() {
        return governmentID;
    }

    public void setGovernmentID(String governmentID) {
        this.governmentID = governmentID;
    }

    public String getBackgroundCheck() {
        return backgroundCheck;
    }

    public void setBackgroundCheck(String backgroundCheck) {
        this.backgroundCheck = backgroundCheck;
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
