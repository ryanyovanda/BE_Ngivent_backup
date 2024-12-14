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
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CreateEventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public CreateEventService(EventRepository eventRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
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
        return eventRepository.findAll(specification , pageable);
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));
    }

    public CreateEventResponseDTO createEvent(CreateEventRequestDTO dto) {
        Category category = categoryRepository.findByCategoryId(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found: " + dto.getCityId()));

        Event event = dto.toEntity(category, city);
        event.setTotalSeats(0);
        event.setSoldSeats(0);
        event.setAvailableSeats(0);
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

    public Event updateEvent(UpdateEventRequestDTO dto) {
        if (dto.getEventId() == null) {
            throw new IllegalArgumentException("Event ID is required for update");
        }

        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found: " + dto.getEventId()));

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));
            event.setCategory(category);
        }

        if (dto.getCityId() != null) {
            City city = cityRepository.findByCityId(dto.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found: " + dto.getCityId()));
            event.setCity(city);
        }

        if (dto.getTitle() != null) event.setTitle(dto.getTitle());
        if (dto.getDescription() != null) event.setDescription(dto.getDescription());
        if (dto.getImageUrl() != null) event.setImageUrl(dto.getImageUrl());

        if (dto.getEventDate() != null) {
            event.setEventDate(dto.getEventDate());
        }

        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));

        eventRepository.delete(event);
    }
}