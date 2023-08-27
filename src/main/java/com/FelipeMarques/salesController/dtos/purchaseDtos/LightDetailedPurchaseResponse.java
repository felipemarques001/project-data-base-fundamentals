package com.FelipeMarques.salesController.dtos.purchaseDtos;

import com.FelipeMarques.salesController.dtos.customerDtos.DetailedCustomerResponse;
import com.FelipeMarques.salesController.models.ProductModel;

import java.math.BigDecimal;

public record LightDetailedPurchaseResponse(Integer id,
                                            ProductModel productModel,
                                            BigDecimal weight) {
}
