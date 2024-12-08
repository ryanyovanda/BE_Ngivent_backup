package com.purwadhika.mini_project.infrastructure.events.controller;

import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.usecase.events.CreateEventUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    //Authorize validation
//    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
    @PostMapping
    public ResponseEntity<?> createEvent(@Validated @RequestBody CreateEventRequestDTO req) {
//        req.setOrganizerId(Claims.getUserIdFromJwt());

        CreateEventResponseDTO responseDTO = createEventUseCase.createEvent(req);
        return Response.successfulResponse("Create event success", responseDTO);
    }
    @GetMapping
    public ResponseEntity<Page<CreateEventResponseDTO>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
        Page<CreateEventResponseDTO> eventPage = createEventUseCase.getAllEvents(pageable);

        return ResponseEntity.ok(eventPage);
    }
}