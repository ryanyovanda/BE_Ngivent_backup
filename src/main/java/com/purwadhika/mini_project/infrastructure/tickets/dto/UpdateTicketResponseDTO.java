package com.purwadhika.mini_project.infrastructure.tickets.dto;

import com.purwadhika.mini_project.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketResponseDTO {
    private Long ticketId;
    private Ticket.Type type;
    private Integer soldTicket;
    private Integer availableTicket;
}
