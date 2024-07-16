package com.ibm.sales.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    @NotBlank
    private String client;

    @NotNull
    @NotEmpty
    private List<ProductOrderRequest> products;
}
