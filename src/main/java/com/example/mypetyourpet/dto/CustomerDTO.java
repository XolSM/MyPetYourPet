package com.example.mypetyourpet.dto;


import com.example.mypetyourpet.model.CustomerInfo;
import com.example.mypetyourpet.model.PetOwnerUser;
import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.model.User;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String email;
    private String location;
    private Integer age;
    private String gender;
    private String governmentID;
    private String backgroundCheck;
    private String registerDate;
    private String customerType;
    private String profilePicture;

    public static CustomerDTO fromUser(User u) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setEmail(u.getEmail());
        dto.setProfilePicture(u.getProfilePicture());

        CustomerInfo info = null;

        if (u instanceof PetOwnerUser owner) {
            info = owner.getCustomerInfo();
            dto.setCustomerType("PetOwner");
        } else if (u instanceof PetSeekerUser seeker) {
            info = seeker.getCustomerInfo();
            dto.setCustomerType("PetSeeker");
        }

        if (info != null) {
            dto.setLocation(info.getLocation());
            dto.setAge(info.getAge());
            dto.setGender(info.getGender());
            dto.setGovernmentID(info.getGovernmentID());
            dto.setBackgroundCheck(info.getBackgroundCheck());
            dto.setRegisterDate(info.getRegisterDate() != null ? info.getRegisterDate().toString() : null);
        }

        return dto;
    }
}
