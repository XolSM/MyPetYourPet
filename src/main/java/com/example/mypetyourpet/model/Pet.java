package com.example.mypetyourpet.model;

import jakarta.persistence.*;


@Entity
@Table(name = "pets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"petName", "customerId"}, name = "petName_owner")
        })
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;
    @Column(nullable = false)
    private String petName;
    @Column(nullable = false)
    private int petAge;
    @Column(nullable = false)
    private boolean petGender;
    @Column(nullable = false)
    private String petBreed;
    @Column(nullable = false)
    private String petBehavior;
    @Column(nullable = false)
    private boolean dewormingUpToDate;
    @Column(nullable = false)
    private boolean vaccinationUpToDate;
    @Column(nullable = false)
    private double petFee;
    @Column(nullable = true)
    private String profilePicture;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private PetProfileStatus petProfileStatus;

    public Pet(){}
    public Pet(String petName, int petAge, boolean petGender,
               String petBreed, String petBehavior, boolean dewormingUpToDate,
               boolean vaccinationUpToDate, double petFee, String profilePicture,
               Long customerId, PetProfileStatus petProfileStatus){
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.petBreed = petBreed;
        this.petBehavior = petBehavior;
        this.dewormingUpToDate = dewormingUpToDate;
        this.vaccinationUpToDate = vaccinationUpToDate;
        this.petFee = petFee;
        this.profilePicture = profilePicture;
        this.customerId = customerId;
        this.petProfileStatus = petProfileStatus;
    }

//    public void updatePetProfile(Long petId, String petBehavior, boolean dewormingUpToDate,
//                                 boolean vaccinationUpToDate, double petFee,
//                                 String profilePicture, PetProfileStatus petProfileStatus){
//
//
//
//    }
//
//    public void deletePetProfile(){
//
//
//
//    }


    public void setPetId(Long id) {
        this.petId = id;
    }

    public Long getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public boolean isPetGender() {
        return petGender;
    }

    public void setPetGender(boolean petGender) {
        this.petGender = petGender;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetBehavior() {
        return petBehavior;
    }

    public void setPetBehavior(String petBehavior) {
        this.petBehavior = petBehavior;
    }

    public boolean isDewormingUpToDate() {
        return dewormingUpToDate;
    }

    public void setDewormingUpToDate(boolean dewormingUpToDate) {
        this.dewormingUpToDate = dewormingUpToDate;
    }

    public boolean isVaccinationUpToDate() {
        return vaccinationUpToDate;
    }

    public void setVaccinationUpToDate(boolean vaccinationUpToDate) {
        this.vaccinationUpToDate = vaccinationUpToDate;
    }

    public double getPetFee() {
        return petFee;
    }

    public void setPetFee(double petFee) {
        this.petFee = petFee;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public PetProfileStatus getPetProfileStatus() {
        return petProfileStatus;
    }

    public void setPetProfileStatus(PetProfileStatus petProfileStatus) {
        this.petProfileStatus = petProfileStatus;
    }
}
