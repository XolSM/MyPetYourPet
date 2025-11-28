// dto/AdminRegistrationRequest.java
package com.example.mypetyourpet.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AdminRegistrationRequest {
    private String firebaseUID;
    private String fullName;
    private String email;
    private String userType = "Administrator";
}
