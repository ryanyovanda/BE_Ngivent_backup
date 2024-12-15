package com.purwadhika.mini_project.infrastructure.discountEvent.repository;

import com.purwadhika.mini_project.entity.DiscountEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DiscountEventRepository extends JpaRepository<DiscountEvent, Long> {
    Optional<DiscountEvent> findByDiscounteId(Long discounteId);
    List<DiscountEvent> findByEventEventId(Long eventId);
}