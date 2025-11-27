package com.example.mypetyourpet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "pet_reservation")
@JsonIgnoreProperties(ignoreUnknown = true) // Prevents JSON conversion issues
public class PetReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto-generate IDs
    @Column(name = "pet_res_id")
    private Long petResId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    @Column(name = "pet_res_status")
    private String petResStatus;
    private Long petId;
    private Long customerId;
    private double serviceFee;
    private double serviceAmount;
    @Transient
    private String petName;
    @Transient
    private String petImage;
    @Transient
    private String petImageUrl;

    public PetReservation() {}
    public Long getPetResId() {
        return petResId;
    }

    public void setPetResId(Long petResId) {
        this.petResId = petResId;
    }

    // Standard getters & setters only
    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getPetResStatus() { return petResStatus; }
    public void setPetResStatus(String petResStatus) { this.petResStatus = petResStatus; }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public double getServiceFee() { return serviceFee; }
    public void setServiceFee(double serviceFee) { this.serviceFee = serviceFee; }

    public double getServiceAmount() { return serviceAmount; }
    public void setServiceAmount(double serviceAmount) { this.serviceAmount = serviceAmount; }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetImageUrl() { return petImageUrl; }
    public void setPetImageUrl(String petImageUrl) { this.petImageUrl = petImageUrl; }
}
