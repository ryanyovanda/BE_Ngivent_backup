package com.purwadhika.mini_project.infrastructure.tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketResponseDTO {
    private Integer type;
    private Integer soldTicket;
    private Integer availableTicket;
}
