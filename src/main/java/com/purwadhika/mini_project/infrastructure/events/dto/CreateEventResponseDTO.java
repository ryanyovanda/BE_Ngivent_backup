package com.purwadhika.mini_project.infrastructure.events.dto;

import com.purwadhika.mini_project.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponseDTO {
    private Long eventId;
//    private Long organizerId;
    private Long categoryId;

    private String imageUrl;
    private String title;
    private String description;
    private String location;
    private OffsetDateTime eventDate;

    private Boolean isFree;
    private BigDecimal price;

    private String startDate;
    private String endDate;

    private Integer allocatedSeats;
    private Integer availableSeats;
    private Integer soldSeats;

    public CreateEventResponseDTO(Long eventId, @NotNull Category category, @Size(max = 100) @NotNull String title, @NotNull String description, @Size(max = 100) @NotNull String location, @NotNull OffsetDateTime eventDate, @NotNull Boolean isFree, BigDecimal price, @NotNull Integer allocatedSeats, Integer availableSeats, Integer soldSeats) {
        this.eventId = eventId;
        this.categoryId = category != null ? category.getCategoryId() : null; // Assuming categoryId is Long in Category
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.isFree = isFree;
        this.price = price;
        this.allocatedSeats = allocatedSeats;
        this.soldSeats = soldSeats;
        this.availableSeats = allocatedSeats-soldSeats;

    }
}