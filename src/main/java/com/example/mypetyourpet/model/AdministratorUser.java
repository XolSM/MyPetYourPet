package com.example.mypetyourpet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "supportUser")
public class AdministratorUser extends User implements Administrator{
    @Column(name="userType")
    private String userType = "Administrator";

    public AdministratorUser() {}

    public AdministratorUser(Long id, String fullName, String email) {
        super(id,fullName, email);
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void inactivateAccount() {

    }

    @Override
    public void createAdminAccount() {

    }

    @Override
    public void createHelpDeskAccount() {

    }

    @Override
    public void runSystemStatusReports() {

    }
}
