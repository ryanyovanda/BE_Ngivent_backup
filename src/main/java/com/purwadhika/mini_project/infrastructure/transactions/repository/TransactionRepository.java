package com.purwadhika.mini_project.infrastructure.transactions.repository;

import com.purwadhika.mini_project.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Fetch all transactions (excluding deleted ones)
    Page<Transaction> findByDeletedAtIsNull(Pageable pageable);

    // Fetch transactions by user
    Page<Transaction> findByUserId(Long userId, Pageable pageable);

    // Fetch transactions by event
    Page<Transaction> findByEventId(Long eventId, Pageable pageable);

    // Fetch transactions based on date range
    @Query("SELECT t FROM Transaction t WHERE t.deletedAt IS NULL AND t.createdAt BETWEEN :startDate AND :endDate")
    Page<Transaction> findTransactionsInDateRange(OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable);
}