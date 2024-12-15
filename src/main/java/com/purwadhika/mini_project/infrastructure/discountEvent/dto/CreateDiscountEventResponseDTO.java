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
public class CreateDiscountEventResponseDTO {
    private Long discounteId;
    private BigDecimal discountPercentage;
    private String title;
    private String description;
    private Integer maxUsage;
    private OffsetDateTime expiredDate;
}
