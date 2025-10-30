package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;

@Entity
public class HelpDeskUser extends User implements HelpDesk{

    public HelpDeskUser(){}
    public HelpDeskUser(Long id, String fullName, String email) {
        super(id,fullName, email);
    }
    @Override
    public void waiveCancellationFee() {

    }

    @Override
    public void cancelPetReservation() {

    }

    @Override
    public void adjustServiceFeeCharge() {

    }

    @Override
    public void viewClaim() {

    }

    @Override
    public void reviewNewCustomerProfile() {

    }

    @Override
    public void returnPayment() {

    }
}
