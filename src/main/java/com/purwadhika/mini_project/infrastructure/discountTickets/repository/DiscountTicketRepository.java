//package com.purwadhika.mini_project.infrastructure.discountTickets.repository;
//
//import com.purwadhika.mini_project.entity.DiscountTicket;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface DiscountTicketRepository extends JpaRepository<DiscountTicket, Integer> {
//    Optional<DiscountTicket> findDiscountTicketByDiscountCode(@Size(max = 50) @NotNull String discountCode);
//}