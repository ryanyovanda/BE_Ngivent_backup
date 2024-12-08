package com.purwadhika.mini_project.infrastructure.events.repository;

import com.purwadhika.mini_project.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Fetch all events that are not soft-deleted
    Page<Event> findByDeletedAtIsNull(Pageable pageable);

    // Fetch active events based on a date range
    @Query("SELECT e FROM Event e WHERE e.deletedAt IS NULL AND e.startDate <= :currentDate AND e.endDate >= :currentDate")
    Page<Event> findActiveEvents(OffsetDateTime currentDate, Pageable pageable);

    // Fetch events by location
    Page<Event> findByLocationContainingIgnoreCase(String location, Pageable pageable);

//    // Fetch events by organizer ID (if applicable)
//    Page<Event> findByOrganizerId(Long organizerId, Pageable pageable);
}