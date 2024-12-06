//package com.purwadhika.mini_project.infrastructure.repository;
//
//
//import com.purwadhika.mini_project.entity.ReferralDiscount;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ReferralDiscountRepository extends JpaRepository<ReferralDiscount,Integer> {
//    Optional<ReferralDiscount> findByCode(String code);
//    List<ReferralDiscount> findUserDiscountsByUserId(Integer userId);
//    @Query("SELECT ud FROM UserDiscount ud WHERE ud.userId = :userId AND ud.isUsed = false AND (ud.expiredAt IS NULL OR ud.expiredAt > CURRENT_TIMESTAMP)")
//    List<ReferralDiscount> findActiveUserDiscountsByUserId(@Param("userId") Integer userId);
//}