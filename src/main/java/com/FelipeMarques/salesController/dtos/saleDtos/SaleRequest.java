package com.FelipeMarques.salesController.dtos.saleDtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleRequest(@NotNull
                             LocalDate saleDate,
                          @NotNull
                             BigDecimal totalValue,
                          @NotNull
                             Boolean paymentStatus,
                          @NotNull
                             Integer customerId) {
}
