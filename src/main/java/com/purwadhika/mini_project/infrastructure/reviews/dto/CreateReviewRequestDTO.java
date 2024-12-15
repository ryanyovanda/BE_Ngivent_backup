package com.purwadhika.mini_project.infrastructure.reviews.dto;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Review;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequestDTO {
    @NotNull
    private Long eventId;

    @NotNull
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    @ColumnDefault("5")
    private Byte rating;

    @Size(max = 255)
    private String feedback;

    public Review toEntity(Event event) {
        Review review = new Review();
        review.setEvent(event);
        review.setRating(rating);
        review.setFeedback(feedback);
        return review;
    }
}
