package com.FelipeMarques.salesController.dtos.saleDtos;

import com.FelipeMarques.salesController.dtos.purchaseDtos.LightDetailedPurchaseResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record LightDetailedSaleResponse(Integer id,
                                        LocalDate saleDate,
                                        BigDecimal totalValue,
                                        Boolean paymentStatus,
                                        List<LightDetailedPurchaseResponse> purchaseList) {
}
