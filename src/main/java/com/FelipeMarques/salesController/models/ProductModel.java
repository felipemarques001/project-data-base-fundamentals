package com.FelipeMarques.salesController.models;

import com.FelipeMarques.salesController.dtos.productDtos.ProductRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "price_per_kg", nullable = false, precision = 5, scale = 2)
    private BigDecimal pricePerKg;

    public void updateData(ProductRequest dto) {
        name = dto.name();
        pricePerKg = dto.pricePerKg();
    }
}
