package com.FelipeMarques.salesController.models;

import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "TB_PURCHASE")
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private SaleModel sale;

    public void updateData(ProductModel newProduct, PurchaseRequest dto, SaleModel newSale) {
        product = newProduct;
        weight = dto.weight();
        sale = newSale;
    }
}
