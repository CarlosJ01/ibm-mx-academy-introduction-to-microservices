package com.ibm.sales.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductOrderRequest {
    @NotNull
    private UUID id;
    @NotNull
    @Min(1)
    private Long quantity;
}
