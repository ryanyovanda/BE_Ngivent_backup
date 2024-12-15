package com.purwadhika.mini_project.infrastructure.tickets.controller;

import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketRequestDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.SearchTicketByIdResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.UpdateTicketResponseDTO;
import com.purwadhika.mini_project.usecase.tickets.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
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

    @PutMapping("/{ticketId}")
    public ResponseEntity<UpdateTicketResponseDTO> updateTicket(
            @PathVariable Long ticketId,
            @RequestParam int soldSeatsIncrement) {

        UpdateTicketResponseDTO responseDTO = ticketService.updateTicket(ticketId, soldSeatsIncrement);

        if (responseDTO.getTicketId() == null || responseDTO.getSoldTicket() == null || responseDTO.getAvailableTicket() == null) {
            return ResponseEntity.badRequest().body(null); // or an appropriate error response
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<SearchTicketByIdResponseDTO>> getTicketsByEventId(@PathVariable Long eventId) {
        try {
            List<SearchTicketByIdResponseDTO> searchTicketByIdResponseDTOS = ticketService.getTicketsByEventId(eventId);
            return ResponseEntity.ok(searchTicketByIdResponseDTOS);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
