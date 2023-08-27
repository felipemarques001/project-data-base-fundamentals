package com.FelipeMarques.salesController.dtos.saleDtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleResponse(Integer id,
                           LocalDate saleDate,
                           BigDecimal totalValue,
                           Boolean paymentStatus,
                           Integer customerId) {
}
