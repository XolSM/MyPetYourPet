package com.example.mypetyourpet.controller;


import com.example.mypetyourpet.dto.TransactionRequest;
import com.example.mypetyourpet.model.Transactions;
import com.example.mypetyourpet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public Transactions createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public Transactions getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);

    }
}

