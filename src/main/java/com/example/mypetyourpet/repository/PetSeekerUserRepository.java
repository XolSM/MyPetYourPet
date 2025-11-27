package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.PetSeekerUser;
import com.example.mypetyourpet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetSeekerUserRepository extends JpaRepository<PetSeekerUser, Long> {
    boolean existsByEmail(String email);

    // to fetch seeker info
    Optional<PetSeekerUser> findByEmail(String email);

    void deleteByEmail(String email); //delete account

    //boolean existsByFirebaseUID(String firebaseUID);
//    List<PetSeekerUser> findByCustomerInfo_ProfileStatusIgnoreCase(String profileStatus);
//    Long countByCustomerInfo_ProfileStatusIgnoreCase(String profileStatus);
//
//    @Query("SELECT u FROM PetSeekerUser u WHERE UPPER(u.customerInfo.profileStatus) = UPPER(:status)")
//    List<User> findAllPending(@Param("status") String status);

}
