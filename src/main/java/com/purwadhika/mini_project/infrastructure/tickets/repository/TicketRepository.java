package com.purwadhika.mini_project.infrastructure.tickets.repository;

import com.purwadhika.mini_project.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByEvent_IdAndTicket_Id(Long eventId, Long ticketId);
}