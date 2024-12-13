//package com.purwadhika.mini_project.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//import java.time.OffsetDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "discounts_referral")
//public class DiscountReferral {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discounts_referral_id_gen")
//    @SequenceGenerator(name = "discounts_referral_id_gen", sequenceName = "discounts_referral_id_seq", allocationSize = 1)
//    @Column(name = "discounts_referral_id", nullable = false)
//    private Long discounts_ReferralId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User referredBy;
//
//    @NotNull
//    @Column(name = "discount_percentage", nullable = false)
//    @Min(value = 0, message = "Discount percentage must be greater than or equal to 0")
//    @Max(value = 100, message = "Discount percentage must be less than or equal to 100")
//    private Double discountPercentage;
//
//    @Column(name = "is_used")
//    private Boolean isUsed;
//
//    @NotNull
//    @Column(name = "end_date", nullable = false)
//    private OffsetDateTime expiredDate;
//
//    @NotNull
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "created_at", nullable = false)
//    private OffsetDateTime createdAt;
//
//    @NotNull
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "updated_at", nullable = false)
//    private OffsetDateTime updatedAt;
//
//    @Column(name = "deleted_at")
//    private OffsetDateTime deletedAt;
//
//}