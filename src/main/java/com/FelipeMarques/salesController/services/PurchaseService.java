package com.FelipeMarques.salesController.services;

import com.FelipeMarques.salesController.dtos.purchaseDtos.DetailedPurchaseResponse;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseRequest;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseResponse;

import java.util.List;

public interface PurchaseService {

    PurchaseResponse savePurchase(PurchaseRequest requestDto);
    List<PurchaseResponse> getAllPurchases();
    PurchaseResponse getPurchaseById(Integer id);
    DetailedPurchaseResponse getDetailedPurchase(Integer id);
    String deletePurchaseById(Integer id);
}
