package com.purwadhika.mini_project.usecase.events;

import com.purwadhika.mini_project.entity.Category;
import com.purwadhika.mini_project.entity.City;
import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.UpdateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.repository.CategoryRepository;
import com.purwadhika.mini_project.infrastructure.events.repository.CityRepository;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.events.specification.FilterEventSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public EventService(EventRepository eventRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    public Page<Event> getAllEvents(int page, int size, String title, String city, String category, String sortField, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortField));

        Specification<Event> specification = Specification.where(null);

        if (category != null) {
            specification = specification.and(FilterEventSpecifications.hasCategory(category));
        }

        if (city != null) {
            specification = specification.and(FilterEventSpecifications.hasCityName(city));
        }
        if (title != null && !title.isEmpty()) {
            specification = specification.and(FilterEventSpecifications.hasTitleName(title));
        }

        Page<Event> events = eventRepository.findAll(specification , pageable);

        if (events.isEmpty()) {
            throw new RuntimeException("No events found with the specified criteria");
        }

        return events;
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));
    }

    public CreateEventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO) {
        Category category = categoryRepository.findByCategoryId(createEventRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + createEventRequestDTO.getCategoryId()));

        City city = cityRepository.findById(createEventRequestDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found: " + createEventRequestDTO.getCityId()));

        Event event = createEventRequestDTO.toEntity(category, city);
        event.setCreatedAt(OffsetDateTime.now());
        event.setUpdatedAt(OffsetDateTime.now());

        Event savedEvent = eventRepository.save(event);

        return new CreateEventResponseDTO(
                savedEvent.getEventId(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getCategory().getCategoryId(),
                savedEvent.getCity().getCityId(),
                savedEvent.getImageUrl(),
                savedEvent.getEventDate()
        );
    }

    public Event updateEvent(UpdateEventRequestDTO updateEventRequestDTO) {
        if (updateEventRequestDTO.getEventId() == null) {
            throw new IllegalArgumentException("Event ID is required for update");
        }

        Event event = eventRepository.findById(updateEventRequestDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found: " + updateEventRequestDTO.getEventId()));

        if (updateEventRequestDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateEventRequestDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + updateEventRequestDTO.getCategoryId()));
            event.setCategory(category);
        }

        if (updateEventRequestDTO.getCityId() != null) {
            City city = cityRepository.findByCityId(updateEventRequestDTO.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found: " + updateEventRequestDTO.getCityId()));
            event.setCity(city);
        }

        if (updateEventRequestDTO.getTitle() != null) event.setTitle(updateEventRequestDTO.getTitle());
        if (updateEventRequestDTO.getDescription() != null) event.setDescription(updateEventRequestDTO.getDescription());
        if (updateEventRequestDTO.getImageUrl() != null) event.setImageUrl(updateEventRequestDTO.getImageUrl());

        if (updateEventRequestDTO.getEventDate() != null) {
            event.setEventDate(updateEventRequestDTO.getEventDate());
        }

        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));

        eventRepository.delete(event);
    }
}