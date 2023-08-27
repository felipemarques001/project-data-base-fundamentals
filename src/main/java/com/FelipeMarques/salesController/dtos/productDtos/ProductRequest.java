package com.FelipeMarques.salesController.dtos.productDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(@NotBlank
                                String name,
                             @NotNull
                                BigDecimal pricePerKg) {
}
