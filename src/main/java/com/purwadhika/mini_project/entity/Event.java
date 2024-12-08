package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_gen")
    @SequenceGenerator(name = "events_id_gen", sequenceName = "events_event_id_seq", allocationSize = 1)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

//    // Many-to-One relationship with User (organizer)
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "organizer_id", nullable = false)
//    private User organizerId;
//
    // Category for the event
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @NotNull
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Size(max = 100)
    @NotNull
    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @NotNull
    @Column(name = "event_date", nullable = false)
    private OffsetDateTime eventDate;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_free", nullable = false)
    private Boolean isFree = false;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @NotNull
    @Column(name = "allocated_seats", nullable = false)
    private Integer allocatedSeats;

    @Column(name = "sold_seats", nullable = false)
    private Integer soldSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    public void calculateAvailableSeats() {
        this.availableSeats = this.allocatedSeats - this.soldSeats;
    }

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

    // Validation for date consistency
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateAfterStartDate() {
        return endDate.isAfter(startDate);
    }
}