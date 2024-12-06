package com.purwadhika.mini_project.infrastructure.events.controller;

import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.events.dto.CreateEventResponseDTO;
import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class test {

    @Autowired
    private EventService eventService; // Service untuk logika bisnis

    // Endpoint untuk membuat event baru
    @PostMapping
    public ResponseEntity<CreateEventResponseDTO> createEvent(@RequestBody CreateEventRequestDTO createEventRequestDTO) {
        // Konversi DTO ke entity
        Event event = createEventRequestDTO.toEntity();

        // Simpan event menggunakan EventService
        Event savedEvent = eventService.saveEvent(event);

        // Konversi event yang sudah disimpan ke ResponseDTO
        CreateEventResponseDTO responseDTO = new CreateEventResponseDTO(savedEvent);

        // Kembalikan response dengan status HTTP 201 Created
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Endpoint untuk mengambil semua event
    @GetMapping
    public ResponseEntity<List<CreateEventResponseDTO>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();

        // Konversi list of events ke list of response DTO
        List<CreateEventResponseDTO> responseDTOs = events.stream()
                .map(CreateEventResponseDTO::new) // Mengonversi setiap event menjadi DTO
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }

    // Endpoint untuk mendapatkan event berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<CreateEventResponseDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);

        // Jika event tidak ditemukan
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        CreateEventResponseDTO responseDTO = new CreateEventResponseDTO(event);
        return ResponseEntity.ok(responseDTO);
    }
}