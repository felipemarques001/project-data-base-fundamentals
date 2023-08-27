package com.FelipeMarques.salesController.controllers;

import com.FelipeMarques.salesController.dtos.productDtos.ProductRequest;
import com.FelipeMarques.salesController.dtos.productDtos.ProductResponse;
import com.FelipeMarques.salesController.mappers.ProductMapper;
import com.FelipeMarques.salesController.models.ProductModel;
import com.FelipeMarques.salesController.repositories.ProductRepository;
import com.FelipeMarques.salesController.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest requestDto ) {
        ProductResponse responseDto = productService.saveProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responseDtoList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(value = "id") Integer id) {
        ProductResponse responseDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") Integer id) {
        String responseMessage = productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") Integer id,
                                                @RequestBody @Valid ProductRequest requestDto) {
        ProductResponse responseDto = productService.updateProduct(id, requestDto);
        return ResponseEntity.status((HttpStatus.OK)).body(responseDto);
    }
}
