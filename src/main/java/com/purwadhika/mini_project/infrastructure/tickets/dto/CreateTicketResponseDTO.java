package com.purwadhika.mini_project.infrastructure.tickets.dto;

import com.purwadhika.mini_project.entity.Ticket;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketResponseDTO {
    private Long ticketId;
    private Long eventId;
    private Ticket.Type type;
    private Boolean isFree;
    private BigDecimal price;
    private Integer totalTicket;
    private Integer soldTicket;
    private Integer availableTicket;
}
