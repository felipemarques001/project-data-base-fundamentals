package com.FelipeMarques.salesController.dtos.productDtos;

import java.math.BigDecimal;

public record ProductResponse(Integer id,
                              String name,
                              BigDecimal pricePerKg) {
}
