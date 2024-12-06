package com.purwadhika.mini_project.infrastructure.events.dto;

import com.purwadhika.mini_project.entity.Event;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDTO {

    @NotNull
    private Long organizerId;

    @NotNull
    private Long categoryId;

    @NotNull
    private String imageUrl;

    @Size(max = 100)
    @NotNull
    private String title;

    @NotNull
    private String description;

    @Size(max = 100)
    @NotNull
    private String location;

    @NotNull
    private String eventDate;

    @NotNull
    private Boolean isFree;

    private BigDecimal price;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    private Integer allocatedSeats;

    public Event toEntity() {
        Event event = new Event();
        event.setImageUrl(this.imageUrl);
        event.setTitle(title);
        event.setDescription(description);
        event.setLocation(location);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        OffsetDateTime eventDate = OffsetDateTime.parse(this.eventDate, formatter);
        event.setEventDate(eventDate);

        OffsetDateTime startDate = OffsetDateTime.parse(this.startDate, formatter);
        event.setStartDate(startDate);

        OffsetDateTime endDate = OffsetDateTime.parse(this.endDate, formatter);
        event.setEndDate(endDate);

        event.setIsFree(isFree);
        event.setPrice(price);
        event.setAllocatedSeats(allocatedSeats);

        return event;
    }
}