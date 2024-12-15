package com.purwadhika.mini_project.infrastructure.tickets.dto;

import com.purwadhika.mini_project.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchTicketByIdResponseDTO {
    private Long ticketId;
    private Ticket.Type type;  // e.g., Regular, VIP
    private BigDecimal price;
    private Integer availableTicket;
}