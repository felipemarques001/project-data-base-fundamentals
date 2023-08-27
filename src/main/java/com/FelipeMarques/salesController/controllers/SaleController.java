package com.FelipeMarques.salesController.controllers;

import com.FelipeMarques.salesController.dtos.saleDtos.DetailedSaleResponse;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleRequest;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleResponse;
import com.FelipeMarques.salesController.mappers.SaleMapper;
import com.FelipeMarques.salesController.models.CustomerModel;
import com.FelipeMarques.salesController.models.SaleModel;
import com.FelipeMarques.salesController.repositories.CustomerRepository;
import com.FelipeMarques.salesController.repositories.SaleRepository;
import com.FelipeMarques.salesController.services.impl.SaleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleServiceImpl service;

    @PostMapping
    public ResponseEntity<SaleResponse> saveSale(@RequestBody @Valid SaleRequest requestDto) {
        SaleResponse responseDto = service.saveSale(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        List<SaleResponse> responseDtoList = service.getAllSales();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable(value = "id") Integer id) {
        SaleResponse responseDto = service.getSaleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<DetailedSaleResponse> getDetailedSale(@PathVariable(value = "id") Integer id) {
        DetailedSaleResponse responseDto = service.getDetailedSale(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSaleById(@PathVariable(value = "id") Integer id) {
        String responseMessage = service.deleteSaleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable(value = "id") Integer id,
                                             @RequestBody SaleRequest requestDto) {
        SaleResponse responseDto = service.updateSale(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{id}/{paymentStatus}")
    public ResponseEntity<SaleResponse> updatePaymentStatus(@PathVariable(value = "id") Integer id,
                                             @PathVariable(value = "paymentStatus") Boolean status) {
       SaleResponse responseDto = service.updatePaymentStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
