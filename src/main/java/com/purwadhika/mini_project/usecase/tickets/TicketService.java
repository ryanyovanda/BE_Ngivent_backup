package com.purwadhika.mini_project.usecase.tickets;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Ticket;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketRequestDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.CreateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.SearchTicketByIdResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.dto.UpdateTicketResponseDTO;
import com.purwadhika.mini_project.infrastructure.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuntimeException("Event not found: " + createTicketRequestDTO.getEventId()));

        Ticket ticket = createTicketRequestDTO.toEntity(event);
        ticket.setSoldTicket(0);
        ticket.setAvailableTicket(ticket.getTotalTicket() - ticket.getSoldTicket());
        ticket.setCreatedAt(OffsetDateTime.now());
        ticket.setUpdatedAt(OffsetDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);

        return new CreateTicketResponseDTO(
                savedTicket.getEvent().getEventId(),
                savedTicket.getType(),
                savedTicket.getIsFree(),
                savedTicket.getPrice(),
                savedTicket.getTotalTicket(),
                savedTicket.getSoldTicket(),
                savedTicket.getAvailableTicket()
        );
    }

    public UpdateTicketResponseDTO updateTicket(Long ticketId, int soldTicketIncrement) {

        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if enough tickets are available
        int newSoldTicket = ticket.getSoldTicket() + soldTicketIncrement;
        if (newSoldTicket> ticket.getTotalTicket()) {
            throw new IllegalArgumentException("Available ticket is sold out");
        }

        // Update ticket fields
        ticket.setSoldTicket(newSoldTicket);
        ticket.setAvailableTicket(ticket.getTotalTicket() - newSoldTicket);
        ticket.setUpdatedAt(OffsetDateTime.now()); // After all updates

        // Save the updated ticket to the database
        ticketRepository.save(ticket);

        // Construct and return the response DTO
        return new UpdateTicketResponseDTO(
                ticket.getTicketId(),
                ticket.getType(),
                ticket.getSoldTicket(),
                ticket.getAvailableTicket()
        );
    }

    public List<SearchTicketByIdResponseDTO> getTicketsByEventId(Long eventId) {
        List<Ticket> tickets = ticketRepository.findByEventEventId(eventId);

        if (tickets.isEmpty()) {
            throw new RuntimeException("No tickets found for ticket ID: " + eventId);
        }

        // Convert entity list to DTO list
        return tickets.stream()
                .map(ticket -> new SearchTicketByIdResponseDTO(
                        ticket.getTicketId(),
                        ticket.getType(),
                        ticket.getPrice(),
                        ticket.getAvailableTicket()
                ))
                .collect(Collectors.toList());
    }
}