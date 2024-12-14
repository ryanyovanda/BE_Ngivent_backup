package com.purwadhika.mini_project.infrastructure.events.controller;


import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.SearchEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.UpdateEventRequestDTO;
import com.purwadhika.mini_project.usecase.events.CreateEventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventService createEventService;

    public EventController(CreateEventService createEventService) {
        this.createEventService = createEventService;
    }

    @GetMapping
    public ResponseEntity<Response<Page<SearchEventResponseDTO>>> getAllEvents(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value="sortField", defaultValue = "eventDate") String sortField,
            @RequestParam(value="sortDirection", defaultValue = "desc") String sortDirection)
        {
        Page<Event> eventsPage = createEventService.getAllEvents(page, size, title, city, category, sortField, sortDirection);
        Page<SearchEventResponseDTO> responseDTOs = eventsPage.map(event -> new SearchEventResponseDTO(
                event.getEventId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory().getName(),
                event.getCity().getName(),
                event.getImageUrl(),
                event.getEventDate()
        ));

        return Response.successfulResponse("Get all events success", responseDTOs);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Response<SearchEventResponseDTO>> getEventById(@PathVariable Long eventId) {
        Event event = createEventService.getEventById(eventId);

        SearchEventResponseDTO responseDTO = new SearchEventResponseDTO(
                event.getEventId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory().getName(),
                event.getCity().getName(),
                event.getImageUrl(),
                event.getEventDate()
        );

        return Response.successfulResponse("Get event by ID success", responseDTO);
    }

    //Authorize validation
//    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
    @PostMapping
    public ResponseEntity<Response<CreateEventResponseDTO>> createEvent(@RequestBody @Valid CreateEventRequestDTO req) {
//        req.setOrganizerId(Claims.getUserIdFromJwt());
        CreateEventResponseDTO responseDTO = createEventService.createEvent(req);
        return Response.successfulResponse("Create event success", responseDTO);
    }

//    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
    @PutMapping("/{eventId}")
    public ResponseEntity<Response<Event>> updateEvent(
//        req.setOrganizerId(Claims.getUserIdFromJwt());
            @PathVariable Long eventId,
            @RequestBody @Valid UpdateEventRequestDTO dto) {

        dto.setEventId(eventId);

        Event updatedEvent = createEventService.updateEvent(dto);
        return Response.successfulResponse("Event updated successfully", updatedEvent);
    }

//    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
    @DeleteMapping("/{eventId}")
//        req.setOrganizerId(Claims.getUserIdFromJwt());
    public ResponseEntity<Response<Void>> deleteEvent(@PathVariable Long eventId) {
        createEventService.deleteEvent(eventId);
        return Response.successfulResponse("Event deleted successfully", null);
    }

}