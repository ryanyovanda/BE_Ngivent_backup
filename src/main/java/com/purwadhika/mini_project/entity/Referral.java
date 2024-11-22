package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "referrals")
public class Referral implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referrals_id_gen")
    @SequenceGenerator(name = "referrals_id_gen", sequenceName = "referrals_referral_id_seq", allocationSize = 1)
    @Column(name = "referral_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "points", nullable = false)
    private Integer points;

    @NotNull
    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @PrePersist
    protected void onCreate() {
        // Logic to calculate expiry date or set a default
        expiresAt = OffsetDateTime.now().plusMonths(3); // Example: 3 months expiration
    }

    @PreUpdate
    protected void onUpdate() {
        // Add logic if necessary
    }

    @PreRemove
    protected void onRemove() {
        // Add logic if necessary
    }
}