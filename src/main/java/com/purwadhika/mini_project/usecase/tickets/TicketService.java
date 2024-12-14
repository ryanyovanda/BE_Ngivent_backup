package com.purwadhika.mini_project.usecase.tickets;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Ticket;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketRequestDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.UpdateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    public CreateTicketResponseDTO createTicket(CreateTicketRequestDTO createTicketRequestDTO) {
        Event event = eventRepository.findByEventId(createTicketRequestDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("City not found: " + createTicketRequestDTO.getEventId()));

        Ticket ticket = createTicketRequestDTO.toEntity(event);
        ticket.setSoldTicket(0);
        ticket.setAvailableTicket(ticket.getTotalTicket() - ticket.getSoldTicket());
        ticket.setCreatedAt(OffsetDateTime.now());
        ticket.setUpdatedAt(OffsetDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);

        return new CreateTicketResponseDTO(
                savedTicket.getTicketId(),
                savedTicket.getEvent().getEventId(),
                savedTicket.getType(),
                savedTicket.getIsFree(),
                savedTicket.getPrice(),
                savedTicket.getTotalTicket(),
                savedTicket.getSoldTicket(),
                savedTicket.getAvailableTicket()
        );
    }

    public UpdateTicketResponseDTO updateTicket(Long eventId, Long ticketId, int soldSeatsIncrement) {
        // Find the ticket by event ID and ticket ID
        Ticket ticket = ticketRepository.findByEvent_IdAndId(eventId, ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if enough tickets are available
        int newSoldSeats = ticket.getSoldTicket() + soldSeatsIncrement;
        if (newSoldSeats > ticket.getTotalTicket()) {
            throw new IllegalArgumentException("Available ticket is sold out");
        }

        // Update ticket fields
        ticket.setSoldTicket(newSoldSeats);
        ticket.setAvailableTicket(ticket.getTotalTicket() - newSoldSeats);
        ticket.setUpdatedAt(OffsetDateTime.now()); // After all updates

        // Save the updated ticket to the database
        ticketRepository.save(ticket);

        // Construct and return the response DTO
        return new UpdateTicketResponseDTO();
    }
}