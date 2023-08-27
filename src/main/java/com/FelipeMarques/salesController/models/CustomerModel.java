package com.FelipeMarques.salesController.models;

import com.FelipeMarques.salesController.dtos.customerDtos.CustomerRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_CUSTOMER")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SaleModel> sales = new ArrayList<>();

    public void updateData(CustomerRequest dto) {
        name = dto.name();
        cpf = dto.cpf();
    }
}
