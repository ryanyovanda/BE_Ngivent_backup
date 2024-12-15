package com.purwadhika.mini_project.usecase.review;

import com.purwadhika.mini_project.entity.Event;
import com.purwadhika.mini_project.entity.Review;
import com.purwadhika.mini_project.infrastructure.events.repository.EventRepository;
import com.purwadhika.mini_project.infrastructure.reviews.dto.CreateReviewRequestDTO;
import com.purwadhika.mini_project.infrastructure.reviews.dto.CreateReviewResponseDTO;
import com.purwadhika.mini_project.infrastructure.reviews.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final EventRepository eventRepository;

    public ReviewService(ReviewRepository reviewRepository, EventRepository eventRepository) {
        this.reviewRepository = reviewRepository;
        this.eventRepository = eventRepository;
    }

    public CreateReviewResponseDTO createReview(CreateReviewRequestDTO createReviewRequestDTO) {
        Event event = eventRepository.findByEventId(createReviewRequestDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found: " + createReviewRequestDTO.getEventId()));

//        boolean reviewExists = reviewRepository.existsByUserIdAndEventId(
//                createReviewRequestDTO.getUserId(),
//                createReviewRequestDTO.getEventId()
//        );
//
//        if (reviewExists) {
//            throw new RuntimeException("You have already reviewed this event");
//        }

        Review review = createReviewRequestDTO.toEntity(event);
        review.setCreatedAt(OffsetDateTime.now());
        review.setUpdatedAt(OffsetDateTime.now());

        Review savedReview = reviewRepository.save(review);

        return new CreateReviewResponseDTO(
                savedReview.getEvent().getEventId(),
                savedReview.getRating(),
                savedReview.getFeedback()
        );
    }

    public List<CreateReviewResponseDTO> getReviewByEventId(Long eventId) {
        List<Review> reviews = reviewRepository.findByEventEventId(eventId);

        if (reviews.isEmpty()) {
            throw new RuntimeException("No review found for event ID: " + eventId);
        }

        return reviews.stream()
                .map(review -> new CreateReviewResponseDTO(
                        review.getReviewId(),
                        review.getRating(),
                        review.getFeedback()
                        ))
                .collect(Collectors.toList());
    }
}
