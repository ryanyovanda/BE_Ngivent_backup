package com.purwadhika.mini_project.infrastructure.discountEvent.dto;

import com.purwadhika.mini_project.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscountEventResponseDTO {
    private Long discounteId;
    private Integer maxUsage;
}
