package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;

@Entity
public class AdministratorUser extends User implements Administrator{

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
