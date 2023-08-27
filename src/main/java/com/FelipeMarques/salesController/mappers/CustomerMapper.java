package com.FelipeMarques.salesController.mappers;

import com.FelipeMarques.salesController.dtos.customerDtos.CustomerRequest;
import com.FelipeMarques.salesController.dtos.customerDtos.CustomerResponse;
import com.FelipeMarques.salesController.dtos.customerDtos.DetailedCustomerResponse;
import com.FelipeMarques.salesController.models.CustomerModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerModel toCustomerModel(CustomerRequest dto) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName(dto.name());
        customerModel.setCpf(dto.cpf());
        return customerModel;
    }

    public static CustomerResponse toCustomerResponse(CustomerModel customerModel) {
        return new CustomerResponse(customerModel.getId(),
                customerModel.getName(), customerModel.getCpf());
    }

    public static DetailedCustomerResponse toDetailedCustomerResponse(CustomerModel customerModel) {
        return new DetailedCustomerResponse(customerModel.getId(),
                customerModel.getName(),
                customerModel.getCpf(),
                SaleMapper.toLightDetailedSaleResponseList(customerModel.getSales()));
    }

    public static List<CustomerResponse> toCustomerResponseList(List<CustomerModel> customerModelList) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerModelList.forEach(customerModel -> {
            customerResponseList.add(toCustomerResponse(customerModel));
        });
        return customerResponseList;
    }
}
