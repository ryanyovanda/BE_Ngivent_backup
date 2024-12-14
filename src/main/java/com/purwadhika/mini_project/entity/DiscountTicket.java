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
//import java.math.BigDecimal;
//import java.time.OffsetDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "discount_tickets")
//public class DiscountTicket {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_tickets_id_gen")
//    @SequenceGenerator(name = "discount_tickets_id_gen", sequenceName = "discount_tickets_id_seq", allocationSize = 1)
//    @Column(name = "discount_tickets_id", nullable = false)
//    private Long discount_tickets_Id;
//
//    @NotNull
//    @Column(name = "discount_percentage", nullable = false)
//    @Min(value = 0, message = "Discount percentage must be greater than or equal to 0")
//    @Max(value = 100, message = "Discount percentage must be less than or equal to 100")
//    private BigDecimal discountPercentage;
//
//    @Size(max = 50)
//    @NotNull
//    @Column(name = "title", nullable = false, length = 50)
//    private String title;
//
//    @Size(max = 255)
//    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
//    private String description;
//
//    @Size(max = 50)
//    @NotNull
//    @Column(name = "discount_code", nullable = false, length = 50)
//    private String discountCode;
//
//    @Column(name = "max_usage", nullable = false)
//    @NotNull
//    private Integer maxUsage;
//
//    @NotNull
//    @Column(name = "start_date", nullable = false)
//    private OffsetDateTime startDate;
//
//    @NotNull
//    @Column(name = "expired_date", nullable = false)
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