package com.FelipeMarques.salesController.services;

import com.FelipeMarques.salesController.dtos.saleDtos.DetailedSaleResponse;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleRequest;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleResponse;

import java.util.List;

public interface SaleService {

    SaleResponse saveSale(SaleRequest requestDto);
    List<SaleResponse> getAllSales();
    SaleResponse getSaleById(Integer id);
    DetailedSaleResponse getDetailedSale(Integer id);
    String deleteSaleById(Integer id);
    SaleResponse updateSale(Integer id, SaleRequest requestDto);
    SaleResponse updatePaymentStatus(Integer id, Boolean status);
}
