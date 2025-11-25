package com.example.mypetyourpet.model;

import jakarta.persistence.*;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(/*set nullable = false later*/ unique = true)
    protected String firebaseUID;

    @Column(name = "full_name")
    protected String fullName;

    protected String email;

    @Column(name = "profile_picture")
    protected String profilePicture;

    @Column(name = "profile_picture_public_id")
    protected String profilePicturePublicId;

    public User() {}

    public User(long uid, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public User(long uid, String fullName, String email, String profilePicture, String profilePicturePublicId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.profilePicturePublicId = profilePicturePublicId;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getProfilePicture() {return profilePicture;}

    public void setProfilePicture(String profilePicture) {this.profilePicture = profilePicture;}

    public String getProfilePicturePublicId() {return profilePicturePublicId;}

    public void setProfilePicturePublicId(String profilePicturePublicId) {this.profilePicturePublicId = profilePicturePublicId;}


    public String getFirebaseUID() {return firebaseUID;}

    public void setFirebaseUID(String firebaseUID) {this.firebaseUID = firebaseUID;}

}
