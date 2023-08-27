package com.FelipeMarques.salesController.dtos.purchaseDtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PurchaseRequest(@NotNull
                                 Integer productId,
                              @NotNull
                                 BigDecimal weight,
                              @NotNull
                                 Integer saleId) {
}
