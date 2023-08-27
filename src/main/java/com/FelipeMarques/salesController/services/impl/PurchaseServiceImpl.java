package com.FelipeMarques.salesController.services.impl;

import com.FelipeMarques.salesController.Exceptions.EntityNotFoundException;
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
import com.FelipeMarques.salesController.services.PurchaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;

    @Transactional
    @Override
    public PurchaseResponse savePurchase(PurchaseRequest requestDto) {
        Optional<ProductModel> savedProductOptional = productRepository.findById(requestDto.productId());
        if(savedProductOptional.isEmpty())
            throw new EntityNotFoundException("Product", "id", String.valueOf(requestDto.productId()));

        Optional<SaleModel> savedSaleOptional = saleRepository.findById(requestDto.saleId());
        if(savedSaleOptional.isEmpty())
            throw new EntityNotFoundException("Sale", "id", String.valueOf(requestDto.saleId()));

        purchaseRepository.savePurchase(requestDto.weight(), requestDto.productId(), requestDto.saleId());
        int lastIndex = purchaseRepository.findAll().size() - 1;
        PurchaseModel savedPurchase = purchaseRepository.findAll().get(lastIndex);

        addSaleValue(savedPurchase, savedSaleOptional.get());

        return PurchaseMapper.toPurchaseResponse(savedPurchase);
    }

    @Override
    public List<PurchaseResponse> getAllPurchases() {
        List<PurchaseModel> savedPurchases = purchaseRepository.findAllPurchases();
        return PurchaseMapper.toPurchaseResponseList(savedPurchases);
    }

    @Override
    public PurchaseResponse getPurchaseById(Integer id) {
        Optional<PurchaseModel> savedPurchaseOptional = purchaseRepository.findById(id);
        if(savedPurchaseOptional.isEmpty())
            throw new EntityNotFoundException("Purchase", "id", String.valueOf(id));

        return PurchaseMapper.toPurchaseResponse(savedPurchaseOptional.get());
    }

    @Override
    public DetailedPurchaseResponse getDetailedPurchase(Integer id) {
        Optional<PurchaseModel> savedPurchaseOptional = purchaseRepository.findById(id);
        if(savedPurchaseOptional.isEmpty())
            throw new EntityNotFoundException("Purchase", "id", String.valueOf(id));

        return PurchaseMapper.toDetailedPurchaseResponse(savedPurchaseOptional.get());
    }

    @Transactional
    @Override
    public String deletePurchaseById(Integer id) {
        Optional<PurchaseModel> savedPurchaseOptional = purchaseRepository.findById(id);
        if(savedPurchaseOptional.isEmpty())
            throw new EntityNotFoundException("Purchase", "id", String.valueOf(id));

        purchaseRepository.deleteById(id);

        subtractSaleValue(savedPurchaseOptional.get(), savedPurchaseOptional.get().getSale());
        return "Purchase deleted successfully.";
    }


    // This method will subtract the Sale's totalValue attribute when a new Purchase was deleted from it
    private void subtractSaleValue(PurchaseModel purchase, SaleModel sale) {
        BigDecimal valuePurchase = purchase.getProduct().getPricePerKg().multiply(purchase.getWeight());
        sale.setTotalValue(sale.getTotalValue().subtract(valuePurchase));
        saleRepository.save(sale);
    }

    // This method will add a new value for the Sale's totalValue attribute when a new Purchase was registered for it
    private void addSaleValue(PurchaseModel purchase, SaleModel sale) {
        BigDecimal valuePurchase = purchase.getProduct().getPricePerKg().multiply(purchase.getWeight());
        sale.setTotalValue(sale.getTotalValue().add(valuePurchase));
        saleRepository.save(sale);
    }
}
