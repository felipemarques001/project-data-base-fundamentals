package com.FelipeMarques.salesController.dtos.purchaseDtos;

import java.math.BigDecimal;

public record PurchaseResponse(Integer id,
                               Integer productId,
                               BigDecimal weight,
                               Integer saleId){
}
