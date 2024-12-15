package com.purwadhika.mini_project.usecase.discountEvent;

import com.purwadhika.mini_project.entity.DiscountEvent;
import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.UpdateDiscountEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.CreateDiscountEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.CreateDiscountEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.discountEvent.repository.DiscountEventRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountEventService {
    private final DiscountEventRepository discountEventRepository;
    private final EventRepository eventRepository;


    public DiscountEventService(DiscountEventRepository discountEventRepository, EventRepository eventRepository) {
        this.discountEventRepository = discountEventRepository;
        this.eventRepository = eventRepository;
    }

    public CreateDiscountEventResponseDTO createDiscountEvent(CreateDiscountEventRequestDTO createDiscountEventRequestDTO) {
        Event event = eventRepository.findByEventId(createDiscountEventRequestDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found: " + createDiscountEventRequestDTO.getEventId()));

        DiscountEvent discountEvent = createDiscountEventRequestDTO.toEntity(event);
        discountEvent.setCreatedAt(OffsetDateTime.now());
        discountEvent.setUpdatedAt(OffsetDateTime.now());

        DiscountEvent savedDiscountEvent = discountEventRepository.save(discountEvent);

        return new CreateDiscountEventResponseDTO(
                savedDiscountEvent.getDiscounteId(),
                savedDiscountEvent.getDiscountPercentage(),
                savedDiscountEvent.getTitle(),
                savedDiscountEvent.getDescription(),
                savedDiscountEvent.getMaxUsage(),
                savedDiscountEvent.getExpiredDate()
        );
    }
    public UpdateDiscountEventResponseDTO updateDiscountEvent(Long discountEvents_Id, int quantityUsage) {

        DiscountEvent discountEvent = discountEventRepository.findByDiscounteId(discountEvents_Id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));

        int newMaxUsage = discountEvent.getMaxUsage() - quantityUsage;
        if (newMaxUsage < 0) {
            throw new IllegalArgumentException("Discount has reached max usage");
        }

        discountEvent.setMaxUsage(newMaxUsage);
        discountEvent.setUpdatedAt(OffsetDateTime.now());

        discountEventRepository.save(discountEvent);

        return new UpdateDiscountEventResponseDTO(
                discountEvent.getDiscounteId(),
                discountEvent.getMaxUsage()
        );
    }

    public List<CreateDiscountEventResponseDTO> getDiscountsByEventId(Long eventId) {
        List<DiscountEvent> discountEvents = discountEventRepository.findByEventEventId(eventId);

        if (discountEvents.isEmpty()) {
            throw new RuntimeException("No discounts found for event ID: " + eventId);
        }

        // Convert entity list to DTO list
        return discountEvents.stream()
                .map(discountEvent -> new CreateDiscountEventResponseDTO(
                        discountEvent.getDiscounteId(),
                        discountEvent.getDiscountPercentage(),
                        discountEvent.getTitle(),
                        discountEvent.getDescription(),
                        discountEvent.getMaxUsage(),
                        discountEvent.getExpiredDate()
                ))
                .collect(Collectors.toList());
    }

}
