package com.purwadhika.mini_project.infrastructure.events.dto;

import com.purwadhika.mini_project.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchEventByIdResponseDTO {
    private Long eventId;
    private String title;
    private String description;
    private String categoryName;
    private String cityName;
    private String imageUrl;
    private OffsetDateTime eventDate;
    private Set<Ticket> tickets;
}