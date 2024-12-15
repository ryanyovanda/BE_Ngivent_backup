package com.purwadhika.mini_project.infrastructure.transactions.repository;

import com.purwadhika.mini_project.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(Long transactionId);
}