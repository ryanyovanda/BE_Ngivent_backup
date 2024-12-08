package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
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
@Table(name = "transactions") // Reverted to 'transaction' table name
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_transaction_id_seq", allocationSize = 1)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    // Many-to-One relationship with User (the customer making the transaction)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User userId;

    // Many-to-One relationship with Event (the event being purchased)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event eventId;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice; // Using BigDecimal for precision in financial calculations

    @Column(name = "discount")
    private BigDecimal discount; // Discount applied to the transaction, if any

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