package com.FelipeMarques.salesController.dtos.customerDtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerRequest(@NotBlank
                                String name,
                              @NotBlank @Length(min = 14, max = 14)
                                String cpf) {
}
