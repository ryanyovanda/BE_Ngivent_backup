package com.purwadhika.mini_project.infrastructure.events.repository;

import com.purwadhika.mini_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional custom query methods can be defined here if needed
}