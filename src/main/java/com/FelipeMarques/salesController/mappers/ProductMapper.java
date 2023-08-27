package com.FelipeMarques.salesController.mappers;

import com.FelipeMarques.salesController.dtos.productDtos.ProductRequest;
import com.FelipeMarques.salesController.dtos.productDtos.ProductResponse;
import com.FelipeMarques.salesController.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductModel toProductModel(ProductRequest dto) {
        ProductModel productModel = new ProductModel();
        productModel.setName(dto.name());
        productModel.setPricePerKg(dto.pricePerKg());
        return productModel;
    }

    public static ProductResponse toProductResponse(ProductModel productModel) {
        return new ProductResponse(productModel.getId(),
                productModel.getName(),
                productModel.getPricePerKg());
    }

    public static List<ProductResponse> toProductResponseList(List<ProductModel> productModelList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productModelList.forEach(productModel -> {
            productResponseList.add(toProductResponse(productModel));
        });
        return productResponseList;
    }
}
