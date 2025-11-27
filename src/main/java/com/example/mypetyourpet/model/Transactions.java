package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(name = "pet_res_id")

    private int petResId;
    @Column(name = "customer_id")
    private Integer customerId;// Nullable
    @Column(name = "claim_id")
    private Integer claimId; // Nullable

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Date transactionDate;
    private double transactionAmount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String paymentMethod;

    private String gatewayTransactionRef; // Change to String


    public Transactions() {}
    public Transactions(Long transactionId, int petResId, int customerId,
                        int claimId, TransactionType transactionType, Date transactionDate,
                        double transactionAmount, TransactionStatus transactionStatus, String note,
                        String paymentMethod, String gatewayTransactionRef) {
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
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

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
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

    public String getGatewayTransactionRef() {
        return gatewayTransactionRef;
    }

    public void setGatewayTransactionRef(String gatewayTransactionRef) {
        this.gatewayTransactionRef = gatewayTransactionRef;
    }

    //    public Long getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(Long transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public int getPetResId() {
//        return petResId;
//    }
//
//    public void setPetResId(int petResId) {
//        this.petResId = petResId;
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public int getClaimId() {
//        return claimId;
//    }
//
//    public void setClaimId(int claimId) {
//        this.claimId = claimId;
//    }
//
//    public String getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
//
//    public Date getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(Date transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//
//    public double getTransactionAmount() {
//        return transactionAmount;
//    }
//
//    public void setTransactionAmount(double transactionAmount) {
//        this.transactionAmount = transactionAmount;
//    }
//
//    public String getTransactionStatus() {
//        return transactionStatus;
//    }
//
//    public void setTransactionStatus(String transactionStatus) {
//        this.transactionStatus = transactionStatus;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public int getGatewayTransactionRef() {
//        return gatewayTransactionRef;
//    }
//
//    public void setGatewayTransactionRef(int gatewayTransactionRef) {
//        this.gatewayTransactionRef = gatewayTransactionRef;
//    }
}
