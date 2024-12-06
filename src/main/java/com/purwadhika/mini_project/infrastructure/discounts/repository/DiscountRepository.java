package com.purwadhika.mini_project.infrastructure.discounts.repository;

import com.purwadhika.mini_project.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    // Fetch a discount by referral code
    Optional<Discount> findByReferralCode(String referralCode);

    // Fetch all active discounts (not expired)
    @Query("SELECT d FROM Discount d WHERE d.deletedAt IS NULL AND d.startDate <= CURRENT_TIMESTAMP AND d.endDate >= CURRENT_TIMESTAMP")
    List<Discount> findActiveDiscounts();
}