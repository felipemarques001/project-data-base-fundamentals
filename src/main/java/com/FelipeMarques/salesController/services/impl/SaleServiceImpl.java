package com.FelipeMarques.salesController.services.impl;

import com.FelipeMarques.salesController.Exceptions.EntityNotFoundException;
import com.FelipeMarques.salesController.dtos.saleDtos.DetailedSaleResponse;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleRequest;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleResponse;
import com.FelipeMarques.salesController.mappers.SaleMapper;
import com.FelipeMarques.salesController.models.CustomerModel;
import com.FelipeMarques.salesController.models.SaleModel;
import com.FelipeMarques.salesController.repositories.CustomerRepository;
import com.FelipeMarques.salesController.repositories.SaleRepository;
import com.FelipeMarques.salesController.services.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public SaleResponse saveSale(SaleRequest requestDto) {
        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(requestDto.customerId());
        if(savedCustomerOptional.isEmpty())
            throw new EntityNotFoundException("Customer", "id", String.valueOf(requestDto.customerId()));

        saleRepository.save(requestDto.saleDate(), requestDto.totalValue(), requestDto.paymentStatus(), requestDto.customerId());
        List<SaleModel> listSales = saleRepository.findAll();
        return SaleMapper.toSaleResponse(listSales.get(listSales.size() - 1));
    }

    @Override
    public List<SaleResponse> getAllSales() {
        List<SaleModel> listSales = saleRepository.findAll();
        return SaleMapper.toSaleResponseList(listSales);
    }

    @Override
    public SaleResponse getSaleById(Integer id) {
        Optional<SaleModel> savedSaleOptional = saleRepository.findById(id);
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(id));

        return SaleMapper.toSaleResponse(savedSaleOptional.get());
    }

    @Override
    public DetailedSaleResponse getDetailedSale(Integer id) {
        Optional<SaleModel> savedSaleOptional = saleRepository.findById(id);
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(id));

        return SaleMapper.toDetailedSaleResponse(savedSaleOptional.get());
    }

    @Transactional
    @Override
    public String deleteSaleById(Integer id) {
        Optional<SaleModel> savedSaleOptional = saleRepository.findById(id);
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(id));

        saleRepository.delete(id);
        return "Sale deleted successfully.";
    }

    @Transactional
    @Override
    public SaleResponse updateSale(Integer id, SaleRequest requestDto) {
        Optional<SaleModel> savedSaleOptional = saleRepository.findById(id);
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(id));

        Optional<CustomerModel> savedCustomerOptional = customerRepository.findById(requestDto.customerId());
        if(savedCustomerOptional.isEmpty())
            throw new EntityNotFoundException("Customer", "id", String.valueOf(requestDto.customerId()));

        saleRepository.updateSale(id, requestDto.saleDate(), requestDto.totalValue(), requestDto.paymentStatus(), requestDto.customerId());
        return new SaleResponse(id, requestDto.saleDate(), requestDto.totalValue(), requestDto.paymentStatus(), requestDto.customerId());
    }

    @Transactional
    @Override
    public SaleResponse updatePaymentStatus(Integer id, Boolean status) {
        Optional<SaleModel> savedSaleOptional = saleRepository.findById(id);
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(id));

        saleRepository.updatePaymentStatus(id, status);
        SaleModel updatedSale = saleRepository.findById(id).get();
        updatedSale.setPaymentStatus(status);
        return SaleMapper.toSaleResponse(updatedSale);
    }
}
