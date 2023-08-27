package com.FelipeMarques.salesController.dtos.purchaseDtos;

import com.FelipeMarques.salesController.models.ProductModel;

import java.math.BigDecimal;

public record DetailedPurchaseResponse(Integer id,
                                       ProductModel productModel,
                                       BigDecimal weight,
                                       Integer saleId) {
}
