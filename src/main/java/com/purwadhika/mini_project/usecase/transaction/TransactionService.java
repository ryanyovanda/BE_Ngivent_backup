package com.purwadhika.mini_project.usecase.transaction;

import com.purwadhika.mini_project.entity.*;
import com.purwadhika.mini_project.infrastructure.discountEvent.repository.DiscountEventRepository;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.tickets.repository.TicketRepository;
import com.purwadhika.mini_project.infrastructure.transactions.dto.CreateTransactionRequestDTO;
import com.purwadhika.mini_project.infrastructure.transactions.dto.CreateTransactionResponseDTO;
import com.purwadhika.mini_project.infrastructure.transactions.repository.TransactionRepository;
import com.purwadhika.mini_project.usecase.discountEvent.DiscountEventService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TransactionService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final DiscountEventRepository discountEventRepository;
    private final TransactionRepository transactionRepository;
    private final DiscountEventService discountEventService;


    public TransactionService(EventRepository eventRepository, TicketRepository ticketRepository, DiscountEventRepository discountEventRepository, TransactionRepository transactionRepository, DiscountEventService discountEventService) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.discountEventRepository = discountEventRepository;
        this.transactionRepository = transactionRepository;
        this.discountEventService = discountEventService;
    }

//    @Transactional
//    public CreateTransactionResponseDTO createTransaction(CreateTransactionRequestDTO createTransactionRequestDTO) {
//        // Retrieve event, ticket, and optional discount entity
//        Event event = eventRepository.findByEventId(createTransactionRequestDTO.getEventId())
//                .orElseThrow(() -> new RuntimeException("Event not found"));
//
//        Ticket ticket = ticketRepository.findByTicketId(createTransactionRequestDTO.getTicketId())
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//
//        BigDecimal discountPercentage = BigDecimal.ZERO;
//        if (createTransactionRequestDTO.getDiscounteId() != null) {
//            DiscountEvent discountEvent = discountEventRepository.findByDiscounteId(createTransactionRequestDTO.getDiscounteId())
//                    .orElseThrow(() -> new RuntimeException("Discount not found"));
//            if (discountEvent != null) {
//                discountPercentage = discountEvent.getDiscountPercentage();
//            }
//        }
//
//        // Update the ticket soldSeats count
//        if (ticket.getAvailableTicket() < createTransactionRequestDTO.getQuantity()) {
//            throw new IllegalArgumentException("Not enough tickets available");
//        }
//
//        ticket.setAvailableTicket(ticket.getAvailableTicket() - createTransactionRequestDTO.getQuantity());
//        ticketRepository.save(ticket);
//
//        BigDecimal ticketPrice = ticket.getPrice();
//        BigDecimal discountAmount = BigDecimal.ZERO;
//
//        if (discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
//            discountAmount = ticketPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
//        }
//
//        BigDecimal finalPrice = ticketPrice.multiply(BigDecimal.valueOf(createTransactionRequestDTO.getQuantity())).subtract(discountAmount);
//
//        Transaction transaction = createTransactionRequestDTO.toEntity(event, ticket, finalPrice);
//
//        transaction.setFinalPrice(finalPrice);
//        transaction.setCreatedAt(OffsetDateTime.now());
//        transaction.setUpdatedAt(OffsetDateTime.now());
//
//        Transaction savedTransaction = transactionRepository.save(transaction);
//
//        return new CreateTransactionResponseDTO(
//                savedTransaction.getTransactionId(),
//                savedTransaction.getEvent().getTitle(),
//                savedTransaction.getTicket().getType().name(),
//                savedTransaction.getDiscountEvent().getDiscountPercentage(),
//                savedTransaction.getQuantity(),
//                savedTransaction.getFinalPrice()
//        );
//    }

    @Transactional
    public CreateTransactionResponseDTO createTransaction(CreateTransactionRequestDTO createTransactionRequestDTO) {
        // Validate input
        if (createTransactionRequestDTO.getEventId() == null || createTransactionRequestDTO.getTicketId() == null || createTransactionRequestDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid input data");
        }

        // Retrieve event, ticket, and optional discount entity
        Event event = eventRepository.findByEventId(createTransactionRequestDTO.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        Ticket ticket = ticketRepository.findByTicketId(createTransactionRequestDTO.getTicketId())
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        DiscountEvent discountEvent = null;
        if (createTransactionRequestDTO.getDiscounteId() != null) {
            discountEvent = discountEventRepository.findByDiscounteId(createTransactionRequestDTO.getDiscounteId())
                    .orElseThrow(() -> new EntityNotFoundException("Discount not found"));
        }

        // Check ticket availability
        if (ticket.getAvailableTicket() < createTransactionRequestDTO.getQuantity()) {
            throw new IllegalArgumentException("Not enough tickets available");
        }

        // Update ticket availability
        ticket.setAvailableTicket(ticket.getAvailableTicket() - createTransactionRequestDTO.getQuantity());
        ticket.setUpdatedAt(OffsetDateTime.now());
        ticketRepository.save(ticket);

        // Calculate final price with optional discount
        BigDecimal ticketPrice = ticket.getPrice();
        Boolean isFree = ticket.getIsFree();
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal finalPrice = BigDecimal.ZERO;


        if (discountEvent != null && !isFree)
        {   discountEventService.updateDiscountEvent(discountEvent.getDiscounteId(),createTransactionRequestDTO.getQuantity());
            BigDecimal discountPercentage = discountEvent.getDiscountPercentage();
            discountAmount = ticketPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        }

        if (!isFree) {
            finalPrice = ticketPrice.multiply(BigDecimal.valueOf(createTransactionRequestDTO.getQuantity())).subtract(discountAmount);
        }

        // Create transaction
        Transaction transaction = createTransactionRequestDTO.toEntity(event, ticket, discountEvent, finalPrice);
        transaction.setFinalPrice(finalPrice);
        transaction.setCreatedAt(OffsetDateTime.now());
        transaction.setUpdatedAt(OffsetDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new CreateTransactionResponseDTO(
                savedTransaction.getTransactionId(),
                savedTransaction.getEvent().getTitle(),
                savedTransaction.getTicket().getType().name(),
                discountEvent != null ? discountEvent.getDiscountPercentage() : BigDecimal.ZERO,
                savedTransaction.getQuantity(),
                savedTransaction.getFinalPrice()
        );
    }

    public List<CreateTransactionResponseDTO> getTransactionById(Long transactionId) {
        Optional<Transaction> transactions = transactionRepository.findByTransactionId(transactionId);

        if (transactions.isEmpty()) {
            throw new RuntimeException("No transaction found for transaction ID: " + transactionId);
        }

        return transactions.stream()
                .map(transaction -> new CreateTransactionResponseDTO(
                        transaction.getTransactionId(),
                        transaction.getEvent().getTitle(),
                        transaction.getTicket().getType().name(),
                        transaction.getDiscountEvent().getDiscountPercentage(),
                        transaction.getQuantity(),
                        transaction.getFinalPrice()
                ))
                .collect(Collectors.toList());
    }
}