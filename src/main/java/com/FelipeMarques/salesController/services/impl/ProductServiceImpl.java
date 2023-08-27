package com.FelipeMarques.salesController.services.impl;

import com.FelipeMarques.salesController.Exceptions.EntityNotFoundException;
import com.FelipeMarques.salesController.Exceptions.ValueAlreadyUsedException;
import com.FelipeMarques.salesController.dtos.productDtos.ProductRequest;
import com.FelipeMarques.salesController.dtos.productDtos.ProductResponse;
import com.FelipeMarques.salesController.mappers.ProductMapper;
import com.FelipeMarques.salesController.models.ProductModel;
import com.FelipeMarques.salesController.repositories.ProductRepository;
import com.FelipeMarques.salesController.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public ProductResponse saveProduct(ProductRequest requestDto) {
        Optional<ProductModel> foundProduct = productRepository.findByName(requestDto.name().toLowerCase());
        if(foundProduct.isPresent())
            throw new ValueAlreadyUsedException("name", requestDto.name());
        productRepository.saveProduct(requestDto.name().toLowerCase(), requestDto.pricePerKg());
        Optional<ProductModel> savedProduct = productRepository.findByName(requestDto.name().toLowerCase());
        return ProductMapper.toProductResponse(savedProduct.get());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductModel> savedProducts = productRepository.findAllProducts();
        return ProductMapper.toProductResponseList(savedProducts);
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Optional<ProductModel> savedProductOptional = productRepository.findById(id);
        if(savedProductOptional.isEmpty())
            throw new EntityNotFoundException("Product", "id", String.valueOf(id));

        return ProductMapper.toProductResponse(savedProductOptional.get());
    }

    @Transactional
    @Override
    public String deleteProductById(Integer id) {
        Optional<ProductModel> savedProductOptional = productRepository.findById(id);
        if(savedProductOptional.isEmpty())
            throw new EntityNotFoundException("Product", "id", String.valueOf(id));

        productRepository.delete(savedProductOptional.get());
        return "Product deleted successfully.";
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest requestDto) {
        Optional<ProductModel> savedProductOptional = productRepository.findById(id);
        if(savedProductOptional.isEmpty())
            throw new EntityNotFoundException("Product", "id", String.valueOf(id));

        Optional<ProductModel> foundProduct = productRepository.findByName(requestDto.name().toLowerCase());
        if(foundProduct.isPresent())
            if(!(savedProductOptional.get().getName().equals(foundProduct.get().getName())))
                throw new ValueAlreadyUsedException("name", requestDto.name());

        productRepository.update(id, requestDto.name().toLowerCase(), requestDto.pricePerKg());
        return new ProductResponse(id, requestDto.name().toLowerCase(), requestDto.pricePerKg());
    }
}
