package com.purwadhika.mini_project.usecase.events.impl;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.User;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.repository.CategoryRepository;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.users.repository.UsersRepository;
import com.purwadhika.mini_project.infrastructure.events.usecase.CreateEventUsecase;
import com.purwadhika.mini_project.common.exceptions.OrganizerNotFoundException;
import com.purwadhika.mini_project.common.exceptions.UnauthorizedOrganizerException;
import com.purwadhika.mini_project.common.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCaseImpl implements CreateEventUsecase {

    private final EventRepository eventRepository;
    private final UsersRepository usersRepository;
    private final CategoryRepository categoryRepository;

    // Constructor injection for repositories
    public CreateEventUseCaseImpl(EventRepository eventRepository, UsersRepository usersRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.usersRepository = usersRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreateEventResponseDTO create(CreateEventRequestDTO req) {
        // Validate the event organizer
        User assignedOrganizer = usersRepository.findById(req.getOrganizerId())
                .orElseThrow(() -> new OrganizerNotFoundException("Organizer not found"));

        // Check if the user is an organizer
        if (!assignedOrganizer.isOrganizer()) {
            throw new UnauthorizedOrganizerException("User is not an organizer");
        }

        // Validate the event category
        var category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Map request DTO to Event entity
        Event event = req.toEntity();
        event.setOrganizer(assignedOrganizer);
        event.setCategory(category);

        // Set default values, such as available seats, if not set
        if (event.getAllocatedSeats() == null) {
            event.setAllocatedSeats(100);  // Example default value for allocated seats
        }
        event.setAvailableSeats(event.getAllocatedSeats());

        // Save the event entity
        Event savedEvent = eventRepository.save(event);

        // Return the response DTO with the event details
        return new CreateEventResponseDTO(
                savedEvent.getId(),
                savedEvent.getOrganizer(),
                savedEvent.getCategory(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getLocation(),
                savedEvent.getEventDate(),
                savedEvent.getIsFree(),
                savedEvent.getPrice(),
                savedEvent.getAllocatedSeats(),
                savedEvent.getAvailableSeats()
        );
    }
}