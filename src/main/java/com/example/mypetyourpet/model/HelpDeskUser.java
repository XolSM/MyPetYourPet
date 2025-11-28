package com.example.mypetyourpet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "support_user")
public class HelpDeskUser extends User implements HelpDesk{

    @Column(name="user_type")
    private String userType = "HelpDesk";


    public HelpDeskUser(){}
    public HelpDeskUser(Long id, String fullName, String email) {
        super(id, fullName, email);
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
