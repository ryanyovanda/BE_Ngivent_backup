package com.purwadhika.mini_project.infrastructure.events.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequestDTO {
    @NotNull
    private Long eventId;

    private Long categoryId;

    @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
    private String title;

    private String description;

    @Size(max = 100)
    private String imageUrl;

    private Long cityId;

    @Future(message = "Event date must be in the future")
    private OffsetDateTime eventDate;

}