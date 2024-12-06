package com.purwadhika.mini_project.infrastructure.users.repository;

import com.purwadhika.mini_project.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> getRolesByNameContainingIgnoreCase(String name);
}
