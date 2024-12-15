package com.purwadhika.mini_project.infrastructure.reviews.controller;

import com.purwadhika.mini_project.common.response.Response;
import com.purwadhika.mini_project.infrastructure.reviews.dto.CreateReviewRequestDTO;
import com.purwadhika.mini_project.infrastructure.reviews.dto.CreateReviewResponseDTO;
import com.purwadhika.mini_project.usecase.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Response<CreateReviewResponseDTO>> createReview(@Valid@RequestBody CreateReviewRequestDTO createReviewRequestDTO) {
        CreateReviewResponseDTO createReviewResponseDTO = reviewService.createReview(createReviewRequestDTO);
        return Response.successfulResponse("Create review success", createReviewResponseDTO);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<CreateReviewResponseDTO>> getReviewByEventId(@PathVariable Long eventId) {
        try {
            List<CreateReviewResponseDTO> createReviewResponseDTOs = reviewService.getReviewByEventId(eventId);
            return ResponseEntity.ok(createReviewResponseDTOs);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
