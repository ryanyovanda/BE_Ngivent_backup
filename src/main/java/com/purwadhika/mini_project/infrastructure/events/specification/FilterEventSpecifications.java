package com.purwadhika.mini_project.infrastructure.events.specification;

import com.purwadhika.mini_project.entity.Event;
import org.springframework.data.jpa.domain.Specification;

public class FilterEventSpecifications {
    public static Specification<Event> hasCategory(String categoryName) {
        return (root, query, criteriaBuilder) -> categoryName == null || categoryName.isEmpty() ? null
                : criteriaBuilder.like(criteriaBuilder.lower(root.get("category").get("name")), "%" + categoryName.toLowerCase() + "%");
    }

    public static Specification<Event> hasCityName(String cityName) {
        return (root, query, criteriaBuilder) -> cityName == null || cityName.isEmpty() ? null
                : criteriaBuilder.equal(criteriaBuilder.lower(root.get("city").get("name")), cityName.toLowerCase());
    }

    public static Specification<Event> hasTitleName(String titleName) {
        return (root, query, criteriaBuilder) -> titleName == null || titleName.isEmpty() ? null
                : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + titleName.toLowerCase() + "%");
    }
}
