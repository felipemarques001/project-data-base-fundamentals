package com.FelipeMarques.salesController.services.impl;

import com.FelipeMarques.salesController.Exceptions.ValueAlreadyUsedException;
import com.FelipeMarques.salesController.Exceptions.EntityNotFoundException;
import com.FelipeMarques.salesController.dtos.customerDtos.CustomerRequest;
import com.FelipeMarques.salesController.dtos.customerDtos.CustomerResponse;
import com.FelipeMarques.salesController.dtos.customerDtos.DetailedCustomerResponse;
import com.FelipeMarques.salesController.mappers.CustomerMapper;
import com.FelipeMarques.salesController.models.CustomerModel;
import com.FelipeMarques.salesController.repositories.CustomerRepository;
import com.FelipeMarques.salesController.services.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerResponse saveCustomer(CustomerRequest requestDto) {
        Optional<CustomerModel> foundCustomer = customerRepository.findByCpf(requestDto.cpf());
        if(foundCustomer.isPresent())
            throw new ValueAlreadyUsedException("CPF", requestDto.cpf());

        customerRepository.saveCustomer(requestDto.name(), requestDto.cpf());
        Optional<CustomerModel> savedCustomer = customerRepository.findByCpf(requestDto.cpf());
        return CustomerMapper.toCustomerResponse(savedCustomer.get());
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<CustomerModel> savedCustomers = customerRepository.findAllCustomers();
        return CustomerMapper.toCustomerResponseList(savedCustomers);
    }

    @Override
    public CustomerResponse getCustomerById(Integer id) {
        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(id);
        if(savedCustomerOptional.isEmpty())
           throw new EntityNotFoundException("Customer", "id", String.valueOf(id));

        return CustomerMapper.toCustomerResponse(savedCustomerOptional.get());
    }

    @Override
    public DetailedCustomerResponse getDetailedCustomerById(Integer id) {
        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(id);
        if(savedCustomerOptional.isEmpty())
            throw new EntityNotFoundException("Customer", "id", String.valueOf(id));

        return CustomerMapper.toDetailedCustomerResponse(savedCustomerOptional.get());
    }

    @Transactional
    @Override
    public String deleteCustomerById(Integer id) {
        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(id);
        if(savedCustomerOptional.isEmpty())
            throw new EntityNotFoundException("Customer", "id", String.valueOf(id));

        customerRepository.deleteById(id);
        return "Customer deleted successfully.";
    }

    @Transactional
    @Override
    public CustomerResponse updateCustomer(Integer id, CustomerRequest requestDto) {
        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(id);
        if(savedCustomerOptional.isEmpty())
            throw new EntityNotFoundException("Customer", "id", String.valueOf(id));

        Optional<CustomerModel> foundCustomer = customerRepository.findByCpf(requestDto.cpf());
        if(foundCustomer.isPresent())
            if(!(savedCustomerOptional.get().getCpf().equals(foundCustomer.get().getCpf())))
                throw new ValueAlreadyUsedException("CPF", requestDto.cpf());

        customerRepository.updateCustomer(id, requestDto.name(), requestDto.cpf());
        return new CustomerResponse(id, requestDto.name(), requestDto.cpf());
    }
}
