package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_id_gen")
    @SequenceGenerator(name = "discount_id_gen", sequenceName = "discount_discount_id_seq", allocationSize = 1)
    @Column(name = "discount_id", nullable = false)
    private Long discountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event eventId;

    @NotNull
    @Column(name = "discount_percentage", nullable = false)
    @Min(value = 0, message = "Discount percentage must be greater than or equal to 0")
    @Max(value = 100, message = "Discount percentage must be less than or equal to 100")
    private BigDecimal discountPercentage;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now(ZoneOffset.ofHours(7)); // WIB (UTC+7)
        updatedAt = OffsetDateTime.now(ZoneOffset.ofHours(7)); // WIB (UTC+7)
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now(ZoneOffset.ofHours(7)); // WIB (UTC+7)
    }

    @PreRemove
    protected void onRemove() {
        deletedAt = OffsetDateTime.now(ZoneOffset.ofHours(7)); // WIB (UTC+7)
    }
}