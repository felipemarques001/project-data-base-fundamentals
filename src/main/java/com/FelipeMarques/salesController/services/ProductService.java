package com.FelipeMarques.salesController.services;

import com.FelipeMarques.salesController.dtos.productDtos.ProductRequest;
import com.FelipeMarques.salesController.dtos.productDtos.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse saveProduct(ProductRequest requestDto);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Integer id);
    String deleteProductById(Integer id);
    ProductResponse updateProduct(Integer id, ProductRequest requestDto);
}
