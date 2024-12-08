package com.purwadhika.mini_project.usecase.events;

import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreateEventUseCase {
    CreateEventResponseDTO createEvent(CreateEventRequestDTO req);
    Page<CreateEventResponseDTO> getAllEvents(Pageable pageable);
}