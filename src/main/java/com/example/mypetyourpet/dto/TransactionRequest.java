package com.example.mypetyourpet.dto;

public class TransactionRequest {
    private int petResId;
    private Integer customerId;
    private Integer claimId;
    private double amount;
    private String transactionType;      // seekertous, ustoowner, ustoseeker
    private String paymentMethod;        // CreditCard, PayPal, etc.
    private String note;

    public TransactionRequest() {}

    // getters & setters

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
