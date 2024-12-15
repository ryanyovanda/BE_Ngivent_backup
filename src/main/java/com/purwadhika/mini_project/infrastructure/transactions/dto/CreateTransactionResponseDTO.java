package com.purwadhika.mini_project.infrastructure.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionResponseDTO {
    private Long transactionId;
    private String eventTitle;
    private String ticketType;
    private BigDecimal discountPercentage;
    private int quantity;
    private BigDecimal finalPrice;
}
