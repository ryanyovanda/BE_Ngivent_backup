package com.purwadhika.mini_project.infrastructure.reviews.repository;

import com.purwadhika.mini_project.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Fetch reviews for a specific event
    Page<Review> findByEventId(Long eventId, Pageable pageable);

    // Fetch reviews for a specific user
    Page<Review> findByUserId(Long userId, Pageable pageable);

    // Fetch all reviews (excluding deleted ones)
    Page<Review> findByDeletedAtIsNull(Pageable pageable);

    // Fetch reviews by rating
    Page<Review> findByRating(Integer rating, Pageable pageable);
}