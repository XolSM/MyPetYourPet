package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Claims {
    @Getter //lombok ? proposed by java
    @Setter //lombok ? proposed by java
    @Id
    private Long claimId;
    private int userId; //HelpDesk
    private int customerId; //PetSeeker or Pet Owner
    private String claimStatus;
    private String claimDetail;
    private Date dateCreated;

    public Claims() {}
    public Claims(Long claimId, int userId, String claimStatus, String claimDetail,
                  Date dateCreated) {
        this.claimId = claimId;
        this.userId = userId;
        this.claimStatus = claimStatus;
        this.claimDetail = claimDetail;
        this.dateCreated = dateCreated;
    }

    public void updateClaim(){




    }

    public void closeClaim(){




    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getClaimDetail() {
        return claimDetail;
    }

    public void setClaimDetail(String claimDetail) {
        this.claimDetail = claimDetail;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
