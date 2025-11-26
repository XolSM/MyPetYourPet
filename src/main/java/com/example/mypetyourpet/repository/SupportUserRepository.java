package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.AdministratorUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportUserRepository extends JpaRepository<AdministratorUser, Long> {
}
