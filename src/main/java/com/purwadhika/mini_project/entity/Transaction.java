//package com.purwadhika.mini_project.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.time.OffsetDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "transactions")
//public class Transaction implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_id_gen")
//    @SequenceGenerator(name = "transactions_id_gen", sequenceName = "transactions_trx_id_seq", allocationSize = 1)
//    @Column(name = "trx_id", nullable = false)
//    private Long id;
//
//    @NotNull
//    @Column(name = "value", nullable = false)
//    private Double value;
//
//    @NotNull
//    @Column(name = "description", nullable = false, length = 500)
//    private String description;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "event_id", nullable = false)
//    private Event event;
//
//    @NotNull
//    @Column(name = "created_at", nullable = false)
//    private OffsetDateTime createdAt;
//
//    @NotNull
//    @Column(name = "updated_at", nullable = false)
//    private OffsetDateTime updatedAt;
//
//    @Column(name = "deleted_at")
//    private OffsetDateTime deletedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = OffsetDateTime.now();
//        updatedAt = OffsetDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = OffsetDateTime.now();
//    }
//
//    @PreRemove
//    protected void onRemove() {
//        deletedAt = OffsetDateTime.now();
//    }
//}