package com.purwadhika.mini_project.usecase.events;

import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;

public interface CreateEventUseCase {
    CreateEventResponseDTO create(CreateEventRequestDTO req);
}