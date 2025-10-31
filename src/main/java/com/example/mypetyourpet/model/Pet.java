package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    private Long petId;
    private String petName;
    private int petAge;
    private boolean petGender;
    private String petBreed;
    private String petBehavior;
    private boolean dewormingUpToDate;
    private boolean vaccinationUpToDate;
    private double petFee;
    private String profilePicture;
    private Long customerId;

    public Pet(){}
    public Pet(Long petId, String petName, int petAge, boolean petGender,
               String petBreed, String petBehavior, boolean dewormingUpToDate,
               boolean vaccinationUpToDate, double petFee, String profilePicture,
               Long customerId){
        this.petId = petId;
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
    }

    public void updatePetProfile(){



    }

    public void deletePetProfile(){



    }


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
}
