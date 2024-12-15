package com.purwadhika.mini_project.infrastructure.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


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
}