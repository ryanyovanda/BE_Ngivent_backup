package com.purwadhika.mini_project.infrastructure.transactions.controller;

import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.infrastructure.transactions.dto.CreateTransactionRequestDTO;
import com.purwadhika.mini_project.infrastructure.transactions.dto.CreateTransactionResponseDTO;
import com.purwadhika.mini_project.usecase.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Response<CreateTransactionResponseDTO>> createTransaction(@Valid @RequestBody CreateTransactionRequestDTO createTransactionRequestDTO) {
        CreateTransactionResponseDTO createTransactionResponseDTO = transactionService.createTransaction(createTransactionRequestDTO);
        return Response.successfulResponse("Create transaction success", createTransactionResponseDTO);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long transactionId) {
        try {
            List<CreateTransactionResponseDTO> createTransactionResponseDTOS = transactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(createTransactionResponseDTOS);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
