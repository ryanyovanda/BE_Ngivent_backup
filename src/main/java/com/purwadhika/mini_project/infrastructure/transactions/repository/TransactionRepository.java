//package com.purwadhika.mini_project.infrastructure.transactions.repository;
//
//import com.purwadhika.mini_project.entity.Transaction;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
//    Optional<Transaction> findByTransactionId(int transactionId);
//
//    List<Transaction> findTransactionsByTicketId(int ticketId);
//
//    List<Transaction> findTransactionsByUserId(int userId);
//
//    Optional<Transaction> findTransactionByTicketIdAndUserId(int ticketId, int userId);
//
//    Optional<Transaction> findTransactionByUserIdAndTicketEvent_Id(int userId, Integer eventId);
//}