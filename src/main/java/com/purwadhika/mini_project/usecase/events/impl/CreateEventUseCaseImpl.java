package com.purwadhika.mini_project.usecase.events.impl;

import com.purwadhika.mini_project.entity.Event;
//import com.purwadhika.mini_project.entity.User;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.repository.CategoryRepository;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
//import com.purwadhika.mini_project.infrastructure.users.repository.UsersRepository;
import com.purwadhika.mini_project.usecase.events.CreateEventUseCase;
//import com.purwadhika.mini_project.common.exceptions.OrganizerNotFoundException;
//import com.purwadhika.mini_project.common.exceptions.UnauthorizedOrganizerException;
import com.purwadhika.mini_project.common.exceptions.CategoryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepository eventRepository;
    //    private final UsersRepository usersRepository;
    private final CategoryRepository categoryRepository;

    // Constructor injection for repositories
    public CreateEventUseCaseImpl(EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
//        this.usersRepository = usersRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreateEventResponseDTO createEvent(CreateEventRequestDTO req) {

//        // Validate the event organizer
//        User assignedOrganizer = usersRepository.findById(req.getOrganizerId()).orElseThrow(() -> new OrganizerNotFoundException("Organizer not found"));
//
//        // Check if the user is an organizer
//        if (!assignedOrganizer.isOrganizer()) {
//            throw new UnauthorizedOrganizerException("User is not an organizer");
//        }

        // Validate the event category
        var category = categoryRepository.findById(req.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Map request DTO to Event entity
        Event event = req.toEntity();
//        event.setOrganizer(assignedOrganizer);
        event.setCategoryId(category);

        // Save the event entity
        Event savedEvent = eventRepository.save(event);

        // Return the response DTO with the event details
        return new CreateEventResponseDTO(
                savedEvent.getEventId(),
//                savedEvent.getOrganizerId(),
                savedEvent.getCategoryId(),
                savedEvent.getImageUrl(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getLocation(),
                savedEvent.getEventDate(),
                savedEvent.getIsFree(),
                savedEvent.getPrice(),
                savedEvent.getAllocatedSeats(),
                savedEvent.getAvailableSeats(),
                savedEvent.getSoldSeats()
        );
    }
    @Override
    public Page<CreateEventResponseDTO> getAllEvents(Pageable pageable) {
        Page<Event> eventPage = eventRepository.findAll(pageable);
        return eventPage.map(event -> new CreateEventResponseDTO(
                event.getEventId(),
//               Event.getOrganizerId(),
                event.getCategoryId(),
                event.getImageUrl(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getEventDate(),
                event.getIsFree(),
                event.getPrice(),
                event.getAllocatedSeats(),
                event.getAvailableSeats(),
                event.getSoldSeats()
        ));
    }
}