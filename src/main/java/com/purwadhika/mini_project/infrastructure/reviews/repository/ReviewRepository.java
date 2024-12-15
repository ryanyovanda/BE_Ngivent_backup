package com.purwadhika.mini_project.infrastructure.reviews.repository;

import com.purwadhika.mini_project.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByEventEventId(Long eventId);
//    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}