//package com.purwadhika.mini_project.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//
//import java.time.OffsetDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_gen")
//    @SequenceGenerator(name = "reviews_id_gen", sequenceName = "reviews_id_seq", allocationSize = 1)
//    @Column(name = "review_id", nullable = false)
//    private Long reviewId;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_id", nullable = false)
////    private User userId;  // The user who left the review
//
//    @NotNull
//    @Min(value = 1, message = "Rating must be between 1 and 5")
//    @Max(value = 5, message = "Rating must be between 1 and 5")
//    @ColumnDefault("5")
//    @Column(name = "rating", nullable = false)
//    private Byte rating;
//
//    @Size(max = 255)
//    @Column(name = "feedback", columnDefinition = "TEXT")
//    private String feedback;
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