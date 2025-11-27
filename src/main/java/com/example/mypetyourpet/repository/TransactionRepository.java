package com.example.mypetyourpet.repository;

import com.example.mypetyourpet.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    // Additional custom queries
}