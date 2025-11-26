package com.example.mypetyourpet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "support_user")
@Getter @Setter
public class AdministratorUser extends User {

    @Column(name = "user_type")
    private String userType = "Administrator";

    @Column(name = "firebaseuid")
    private String firebaseuid;

    @Column(name = "profile_picture_public_id")
    private String profilePicturePublicId;

    public AdministratorUser() {}
}
