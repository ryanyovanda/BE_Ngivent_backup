package com.purwadhika.mini_project.infrastructure.events.dto;

import com.purwadhika.mini_project.entity.Category;
import com.purwadhika.mini_project.entity.City;
import com.purwadhika.mini_project.entity.Event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDTO {

    @NotNull
    private Long categoryId;

    @NotNull
    private Long cityId;

    @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
    @NotNull(message = "Title is required")
    private String title;

    @NotNull
    private String description;

    @Size(max = 100)
    @NotNull
    private String imageUrl;

    @Future(message = "Event date must be in the future")
    @NotNull
    private OffsetDateTime eventDate;

    public Event toEntity(Category category, City city) {
        Event event = new Event();
        event.setCategory(category);
        event.setCity(city);
        event.setTitle(title);
        event.setDescription(description);
        event.setImageUrl(imageUrl);
        event.setEventDate(eventDate);
        return event;
    }
}