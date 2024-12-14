package com.purwadhika.mini_project.infrastructure.tickets.dto;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Ticket;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequestDTO {
    @NotNull
    private Long eventId;

    private Ticket.Type type;

    @NotNull
    private Boolean isFree;

    private BigDecimal price;

    @NotNull
    private Integer totalTicket;

    private Integer soldTicket;

    public Ticket toEntity(Event event) {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setType(type);
        ticket.setIsFree(isFree);
        ticket.setPrice(price);
        ticket.setTotalTicket(totalTicket);
        ticket.setSoldTicket(soldTicket);
        return ticket;
    }
}
