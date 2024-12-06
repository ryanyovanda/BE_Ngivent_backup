package com.purwadhika.mini_project.infrastructure.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponseDTO {
    private Long eventId;
    private Long organizerId;
    private Long categoryId;

    private String imageUrl;
    private String title;
    private String description;
    private String location;
    private String eventDate;

    private Boolean isFree;
    private BigDecimal price;

    private String startDate;
    private String endDate;

    private Integer allocatedSeats;
    private Integer availableSeats;
    private Integer soldSeats;

    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}