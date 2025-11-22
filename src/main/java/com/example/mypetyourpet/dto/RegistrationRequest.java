package com.example.mypetyourpet.dto;

public class RegistrationRequest {
    //setUp firebase ID later
    private String firebaseUID;
    private String role;
    private String fullName;
    private String email;
    private String phone;
    private String governmentId;
    private String location;
    private String gender;
    private String profilePic;
    private String profilePicturePublicId;
    public String bio;
    private Integer age;

    public RegistrationRequest(){ }
    public RegistrationRequest(String firebaseUID, String fullName, String email, String phone, int age, String gender,
                               String governmentId, String location, String bio){
        this.firebaseUID = firebaseUID; //might not need this here??
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.governmentId = governmentId;
        this.location = location;
        this.bio = bio;
    }
    public RegistrationRequest(String firebaseUID, String fullName, String email, String phone, int age, String gender,
                               String governmentId, String location, String ProfilePic,String bio,
                               String ProfilePicturePublicId){
        this.firebaseUID = firebaseUID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.governmentId = governmentId;
        this.location = location;
        this.profilePic = ProfilePic;
        this.bio = bio;
        this.profilePicturePublicId = ProfilePicturePublicId;
    }

    public String getFirebaseUID() {return firebaseUID;}

    public void setFirebaseUID(String firebaseUID) {this.firebaseUID = firebaseUID;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicturePublicId() {
        return profilePicturePublicId;
    }

    public void setProfilePicturePublicId(String profilePicturePublicId) {
        this.profilePicturePublicId = profilePicturePublicId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
