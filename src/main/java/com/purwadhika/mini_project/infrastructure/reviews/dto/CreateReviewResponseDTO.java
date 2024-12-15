package com.purwadhika.mini_project.infrastructure.reviews.dto;

import com.purwadhika.mini_project.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewResponseDTO {
    private Long eventId;
    private Byte rating;
    private String feedback;
}
