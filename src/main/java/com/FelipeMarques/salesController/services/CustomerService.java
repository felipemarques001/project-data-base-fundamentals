package com.FelipeMarques.salesController.services;

import com.FelipeMarques.salesController.dtos.customerDtos.CustomerRequest;
import com.FelipeMarques.salesController.dtos.customerDtos.CustomerResponse;
import com.FelipeMarques.salesController.dtos.customerDtos.DetailedCustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest requestDto);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Integer id);
    DetailedCustomerResponse getDetailedCustomerById(Integer id);
    String deleteCustomerById(Integer id);
    CustomerResponse updateCustomer(Integer id, CustomerRequest requestDto);
}
