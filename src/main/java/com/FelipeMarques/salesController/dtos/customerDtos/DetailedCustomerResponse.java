package com.FelipeMarques.salesController.dtos.customerDtos;

import com.FelipeMarques.salesController.dtos.saleDtos.LightDetailedSaleResponse;

import java.util.List;

public record DetailedCustomerResponse(Integer id,
                                       String name,
                                       String cpf,
                                       List<LightDetailedSaleResponse> saleList) {
}
