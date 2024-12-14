package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tickets_id_gen")
    @SequenceGenerator(name = "tickets_id_gen", sequenceName = "tickets_id_seq", allocationSize = 1)
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

//    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<DiscountTicket> discountTickets = new LinkedHashSet<>();

    @Size(max = 50)
    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_free", nullable = false)
    private Boolean isFree = false;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "total_ticket", nullable = false)
    private Integer totalTicket;

    @Column(name = "sold_ticket", nullable = false)
    @ColumnDefault("0")
    private Integer soldTicket;

    @Column(name = "available_ticket", nullable = false)
    private Integer availableTicket;

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

}