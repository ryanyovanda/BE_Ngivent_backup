//package com.purwadhika.mini_project.infrastructure.events.controller;
//
//import com.purwadhika.mini_project.common.response.Response;
//import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
//import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
//import com.purwadhika.mini_project.entity.Event;
//import com.purwadhika.mini_project.usecase.events.CreateEventUseCase;
//import com.purwadhika.mini_project.service.EventService;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/events")
//public class test {
//
//    private final CreateEventUseCase createEventUseCase;
//    private final EventService eventService;
//
//    public test(CreateEventUseCase createEventUseCase, EventService eventService) {
//        this.createEventUseCase = createEventUseCase;
//        this.eventService = eventService;
//    }
//
////Authorize validation
////    @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
//    @PostMapping
//    public ResponseEntity<?> createEvent(@Validated @RequestBody CreateEventRequestDTO req) {
//        // req.setOrganizerId(Claims.getUserIdFromJwt());
//        CreateEventResponseDTO responseDTO = createEventUseCase.create(req);
//        return Response.successfulResponse("Create event success", responseDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<CreateEventResponseDTO>> getAllEvents(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "eventDate,desc") String sort) {
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
//        Page<Event> eventPage = eventService.getAllEvents(pageable);
//        Page<CreateEventResponseDTO> responseDTOPage = eventPage.map(CreateEventResponseDTO::new);
//
//        return ResponseEntity.ok(responseDTOPage);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CreateEventResponseDTO> getEventById(@PathVariable Long id) {
//        Event event = eventService.getEventById(id);
//
//        if (event == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        CreateEventResponseDTO responseDTO = new CreateEventResponseDTO(event);
//        return ResponseEntity.ok(responseDTO);
//    }
//}