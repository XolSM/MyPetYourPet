package com.example.mypetyourpet.service;

import com.example.mypetyourpet.dto.TransactionRequest;
import com.example.mypetyourpet.model.Transactions;
import com.example.mypetyourpet.model.TransactionType;
import com.example.mypetyourpet.model.TransactionStatus;
import com.example.mypetyourpet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionsRepository;

    /**
     * Creates any type of transaction:
     *  - seeker → platform
     *  - platform → owner
     *  - platform → seeker (refund)
     */
    public Transactions createTransaction(TransactionRequest request) {

        Transactions t = new Transactions();

        t.setPetResId(request.getPetResId());
        t.setCustomerId(request.getCustomerId());
        t.setClaimId(request.getClaimId());
        t.setTransactionAmount(request.getAmount());
        t.setTransactionType(TransactionType.valueOf(request.getTransactionType()));
        t.setPaymentMethod(request.getPaymentMethod());
        t.setNote(request.getNote());

        // AUTO DATE — here!
        t.setTransactionDate(new Date());

        // Fake gateway ID for now
        t.setGatewayTransactionRef("TX-" + UUID.randomUUID());

        // Default status
        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        return transactionsRepository.save(t);
    }

    public Transactions getTransaction(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
