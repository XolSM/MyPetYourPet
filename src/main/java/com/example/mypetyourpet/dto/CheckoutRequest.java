package com.example.mypetyourpet.dto;

public class CheckoutRequest {

    private Long reservationId; // optional for now
    private Long amountCents;   // e.g. 2500 = 25.00 CAD
    private String description = "Payment for your reservation"; // e.g. "Buddy - 2hr hangout"

    public Long getReservationId() { return reservationId; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }

    public Long getAmountCents() { return amountCents; }
    public void setAmountCents(Long amountCents) { this.amountCents = amountCents; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
