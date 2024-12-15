package com.purwadhika.mini_project.infrastructure.transactions.dto;

import com.purwadhika.mini_project.entity.DiscountEvent;
import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Ticket;
import com.purwadhika.mini_project.entity.Transaction;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequestDTO {
    @NotNull
    private Long eventId;

    @NotNull
    private Long ticketId;

    private Long discounteId;

    @NotNull
    private int quantity;

    public Transaction toEntity(Event event, Ticket ticket, DiscountEvent discountEvent, BigDecimal finalPrice) {
        Transaction transaction = new Transaction();
        transaction.setEvent(event);
        transaction.setTicket(ticket);
        transaction.setDiscountEvent(discountEvent);
        transaction.setFinalPrice(finalPrice);
        transaction.setQuantity(quantity);
        return transaction;
    }
}