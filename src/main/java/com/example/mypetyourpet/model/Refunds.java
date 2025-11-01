package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Refunds {
    @Id
    private Long refundId;
    private int claimId;
    private int petResId;
    private Long transactionId;
    private String refundType;
    private double refundAmount;
    private String reason;
    private Date refundDate;

    public Refunds() {}
    public Refunds(Long refundId, int claimId, int petResId, Long transactionId,
                   String refundType, double refundAmount, String reason,
                   Date refundDate) {
        this.refundId = refundId;
        this.claimId = claimId;
        this.petResId = petResId;
        this.transactionId = transactionId;
        this.refundType = refundType;
        this.refundAmount = refundAmount;
        this.reason = reason;
        this.refundDate = refundDate;
    }

    public void setRefundId(Long id) {
        this.refundId = id;
    }

    public Long getRefundId() {
        return refundId;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getPetResId() {
        return petResId;
    }

    public void setPetResId(int petResId) {
        this.petResId = petResId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

 }
