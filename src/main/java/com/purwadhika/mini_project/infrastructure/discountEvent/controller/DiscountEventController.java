package com.purwadhika.mini_project.infrastructure.discountEvent.controller;

import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.CreateDiscountEventRequestDTO;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.CreateDiscountEventResponseDTO;
import com.purwadhika.mini_project.infrastructure.discountEvent.dto.UpdateDiscountEventResponseDTO;
import com.purwadhika.mini_project.usecase.discountEvent.DiscountEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounte")
public class DiscountEventController {
    private final DiscountEventService discountEventService;

    public DiscountEventController(DiscountEventService discountEventService) {
        this.discountEventService = discountEventService;
    }

    @PostMapping
    public ResponseEntity<Response<CreateDiscountEventResponseDTO>> createDiscountEvent(@Valid @RequestBody CreateDiscountEventRequestDTO createDiscountEventRequestDTO) {
        CreateDiscountEventResponseDTO createDiscountEventResponseDTO = discountEventService.createDiscountEvent(createDiscountEventRequestDTO);
        return Response.successfulResponse("Create discount success", createDiscountEventResponseDTO);
    }

    @PutMapping("/{discounteId}")
    public ResponseEntity<UpdateDiscountEventResponseDTO> updateDiscountEvent (
        @PathVariable Long discounteId,
        @RequestParam int quantityUsage) {

        UpdateDiscountEventResponseDTO responseDTO = discountEventService.updateDiscountEvent(discounteId, quantityUsage);

        if (responseDTO.getDiscounteId() == null || responseDTO.getMaxUsage() == null) {
        return ResponseEntity.badRequest().body(null);
    }
    return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<CreateDiscountEventResponseDTO>> getDiscountsByEventId(@PathVariable Long eventId) {
        try {
            List<CreateDiscountEventResponseDTO> discountResponseDTOs = discountEventService.getDiscountsByEventId(eventId);
            return ResponseEntity.ok(discountResponseDTOs);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
