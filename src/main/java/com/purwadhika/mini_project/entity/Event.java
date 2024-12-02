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
//@Table(name = "events")
//public class Event implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_gen")
//    @SequenceGenerator(name = "events_id_gen", sequenceName = "events_event_id_seq", allocationSize = 1)
//    @Column(name = "event_id", nullable = false)
//    private Long id;
//
//    @NotNull
//    @Column(name = "name", nullable = false, length = 100)
//    private String name;
//
//    @NotNull
//    @Column(name = "price", nullable = false)
//    private Double price;
//
//    @NotNull
//    @Column(name = "date", nullable = false)
//    private OffsetDateTime date;
//
//    @NotNull
//    @Column(name = "location", nullable = false, length = 200)
//    private String location;
//
//    @Column(name = "description", length = 1000)
//    private String description;
//
//    @NotNull
//    @Column(name = "available_seats", nullable = false)
//    private Integer availableSeats;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "organizer_id")
//    private User organizer;
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