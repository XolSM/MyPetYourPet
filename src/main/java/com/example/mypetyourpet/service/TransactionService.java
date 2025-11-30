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

    public Transactions createTransaction(TransactionRequest request) {

        Transactions t = new Transactions();

        t.setPetResId(request.getPetResId());
        t.setCustomerId(request.getCustomerId());
        t.setClaimId(request.getClaimId());
        t.setTransactionAmount(request.getAmount());
        t.setTransactionType(TransactionType.valueOf(request.getTransactionType()));
        t.setPaymentMethod(request.getPaymentMethod());
        t.setNote(request.getNote());


        t.setTransactionDate(new Date());


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
