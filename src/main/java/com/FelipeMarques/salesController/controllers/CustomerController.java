package com.FelipeMarques.salesController.controllers;

import com.FelipeMarques.salesController.dtos.customerDtos.CustomerRequest;
import com.FelipeMarques.salesController.dtos.customerDtos.CustomerResponse;
import com.FelipeMarques.salesController.dtos.customerDtos.DetailedCustomerResponse;
import com.FelipeMarques.salesController.services.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerRequest requestDto) {
        CustomerResponse responseDto = customerService.saveCustomer(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> responseDtoList = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") Integer id) {
        CustomerResponse responseDto = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<DetailedCustomerResponse> getDetailedCustomerById(@PathVariable(value = "id") Integer id) {
        DetailedCustomerResponse responseDto = customerService.getDetailedCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(value = "id") Integer id) {
        String responseMessage = customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid CustomerRequest requestDto) {
        CustomerResponse responseDto = customerService.updateCustomer(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
