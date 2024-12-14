package com.purwadhika.mini_project.infrastructure.tickets.controller;

import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketRequestDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.UpdateTicketResponseDTO;
import com.purwadhika.mini_project.usecase.tickets.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;

    }

    @PostMapping
    public ResponseEntity<Response<CreateTicketResponseDTO>> createTicket(@Valid @RequestBody CreateTicketRequestDTO createTicketRequestDTO) {
        CreateTicketResponseDTO createTicketResponseDTO = ticketService.createTicket(createTicketRequestDTO);
        return Response.successfulResponse("Create ticket success", createTicketResponseDTO);
    }

    @PutMapping("/{ticketId}/sold-seats")
    public ResponseEntity<UpdateTicketResponseDTO> updateTicket(
            @PathVariable Long eventId,
            @PathVariable Long ticketId,
            @RequestParam int soldSeatsIncrement) {

        UpdateTicketResponseDTO responseDTO = ticketService.updateTicket(eventId, ticketId, soldSeatsIncrement);

        return ResponseEntity.ok(responseDTO);
    }

}
