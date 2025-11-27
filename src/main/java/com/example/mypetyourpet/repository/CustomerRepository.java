package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM customer u WHERE UPPER(u.customerInfo.profileStatus) = UPPER(:status)")
//    List<User> findAllPending(@Param("status") String status);
//
//    @Query("SELECT COUNT(u) FROM customer u WHERE " +
//            "(TYPE(u) = com.example.mypetyourpet.model.PetOwnerUser OR " +
//            " TYPE(u) = com.example.mypetyourpet.model.PetSeekerUser) " +
//            "AND UPPER(u.customerInfo.profileStatus) = UPPER(:status)")
//    Long countPending(@Param("status") String status);
}
