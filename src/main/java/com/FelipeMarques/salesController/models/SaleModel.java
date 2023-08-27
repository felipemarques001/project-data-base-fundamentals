package com.FelipeMarques.salesController.models;

import com.FelipeMarques.salesController.dtos.saleDtos.SaleRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_SALE")
public class SaleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    @Column(name = "total_value", nullable = false, precision = 6, scale = 2)
    private BigDecimal totalValue;

    @Column(name = "payment_status", nullable = false)
    private Boolean paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<PurchaseModel> purchases = new ArrayList<>();

    public void updateData(SaleRequest dto, CustomerModel newCustomer) {
        saleDate = dto.saleDate();
        totalValue = dto.totalValue();
        paymentStatus = dto.paymentStatus();
        customer = newCustomer;
    }
}
