package com.purwadhika.mini_project.infrastructure.discountEvent.dto;

import com.purwadhika.mini_project.entity.DiscountEvent;
import com.purwadhika.mini_project.entity.Event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountEventRequestDTO {
    @NotNull
    private Long eventId;

    @NotNull
    private BigDecimal discountPercentage;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Integer maxUsage;

    @Future(message = "Event date must be in the future")
    private OffsetDateTime expiredDate;

    public DiscountEvent toEntity(Event event) {
        DiscountEvent discountEvent = new DiscountEvent();
        discountEvent.setEvent(event);
        discountEvent.setDiscountPercentage(discountPercentage);
        discountEvent.setTitle(title);
        discountEvent.setDescription(description);
        discountEvent.setMaxUsage(maxUsage);
        discountEvent.setExpiredDate(expiredDate);
        return discountEvent;
    }
}