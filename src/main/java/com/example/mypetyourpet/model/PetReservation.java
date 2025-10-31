package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class PetReservation {
    @Id
    private Long petResId; // Primary Key
    private Date dateCreated;
    private Date startDate;
    private Date endDate;
    private String petResStatus; // Pending, Rejected, Confirmed-Pending, Confirmed-Paid
    private Long petId; // Foreign Key -> Pet.petId
    private Long customerId; // Seeker -  Foreign Key -> Customer. customerId
    private double serviceFee;
    private double serviceAmount;

    public PetReservation() {}
    public PetReservation(Long petResId, Date dateCreated, Date startDate,
                          Date endDate, String petResStatus, Long petId,
                          Long customerId, double serviceFee,
                          double serviceAmount){
        this.petResId = petResId;
        this.dateCreated = dateCreated;
        this.startDate = startDate;
        this.endDate = endDate;
        this.petResStatus = petResStatus;
        this.petId = petId;
        this.customerId = customerId;
        this.serviceFee = serviceFee;
        this.serviceAmount = serviceAmount;

    }

    public void updateStatus(){



    }

    public void completePayment(){



    }
    public void setpetResId(Long id) {
        this.petResId = id;
    }

    public Long getpetResId() {
        return petResId;
    }

    public Long getPetResId() {
        return petResId;
    }

    public void setPetResId(Long petResId) {
        this.petResId = petResId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPetResStatus() {
        return petResStatus;
    }

    public void setPetResStatus(String petResStatus) {
        this.petResStatus = petResStatus;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long seekerCustomerId) {
        this.customerId = seekerCustomerId;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
}
