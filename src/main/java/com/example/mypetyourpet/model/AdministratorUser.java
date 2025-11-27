package com.example.mypetyourpet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supportUser")
@Getter @Setter
public class AdministratorUser extends User{

    @Column(name = "userType")
    private String userType = "Administrator";


    @Column(name = "profile_picture_public_id")
    private String profilePicturePublicId;

    public AdministratorUser() {}

}
