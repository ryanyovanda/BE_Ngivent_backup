package com.purwadhika.mini_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_gen")
    @SequenceGenerator(name = "roles_id_gen", sequenceName = "roles_role_id_seq", allocationSize = 1)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        // Add logic if necessary
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