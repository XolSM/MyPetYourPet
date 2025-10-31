package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private int petResId; //
    private int customerId; //PetSeeker or Pet Owner
    private int claimId;
    private String transactionType;
    private Date transactionDate;
    private double transactionAmount;
    private String transactionStatus;
    private String note;
    private String paymentMethod;
    private int gatewayTransactionRef;


    public Transactions() {}
    public Transactions(Long transactionId, int petResId, int customerId,
                        int claimId, String transactionType, Date transactionDate,
                        double transactionAmount, String transactionStatus, String note,
                        String paymentMethod, int gatewayTransactionRef) {
        this.transactionId = transactionId;
        this.petResId = petResId;
        this.customerId = customerId;
        this.claimId = claimId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
        this.note = note;
        this.paymentMethod = paymentMethod;
        this.gatewayTransactionRef = gatewayTransactionRef;
    }


    public void returnPayment() {




    }
    public void depositPayment(){




    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public int getPetResId() {
        return petResId;
    }

    public void setPetResId(int petResId) {
        this.petResId = petResId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getGatewayTransactionRef() {
        return gatewayTransactionRef;
    }

    public void setGatewayTransactionRef(int gatewayTransactionRef) {
        this.gatewayTransactionRef = gatewayTransactionRef;
    }
}
