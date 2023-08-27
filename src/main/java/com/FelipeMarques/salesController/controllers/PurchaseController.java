package com.FelipeMarques.salesController.controllers;


import com.FelipeMarques.salesController.dtos.purchaseDtos.DetailedPurchaseResponse;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseRequest;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseResponse;
import com.FelipeMarques.salesController.mappers.PurchaseMapper;
import com.FelipeMarques.salesController.models.ProductModel;
import com.FelipeMarques.salesController.models.PurchaseModel;
import com.FelipeMarques.salesController.models.SaleModel;
import com.FelipeMarques.salesController.repositories.ProductRepository;
import com.FelipeMarques.salesController.repositories.PurchaseRepository;
import com.FelipeMarques.salesController.repositories.SaleRepository;
import com.FelipeMarques.salesController.services.impl.PurchaseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> savePurchase(@RequestBody @Valid PurchaseRequest requestDto) {
        PurchaseResponse responseDto = purchaseService.savePurchase(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases() {
        List<PurchaseResponse> responseDtoList = purchaseService.getAllPurchases();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(@PathVariable(value = "id") Integer id) {
        PurchaseResponse responseDto = purchaseService.getPurchaseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<DetailedPurchaseResponse> getDetailedPurchaseById(@PathVariable(value = "id") Integer id) {
        DetailedPurchaseResponse responseDto = purchaseService.getDetailedPurchase(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseById(@PathVariable(value = "id") Integer id) {
        String responseMessage = purchaseService.deletePurchaseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
