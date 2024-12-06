package com.purwadhika.mini_project.infrastructure.events.controller;

import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.usecase.events.CreateEventUseCase;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    //Authorize validation
    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
    @PostMapping
    public ResponseEntity<?> createEvent(@Validated @RequestBody CreateEventRequestDTO req) {
        req.setOrganizerId(Claims.getUserIdFromJwt());

        // Business logic, like creating an event
        CreateEventResponseDTO responseDTO = createEventUseCase.create(req);
        return Response.successfulResponse("Create event success", responseDTO);
    }
}