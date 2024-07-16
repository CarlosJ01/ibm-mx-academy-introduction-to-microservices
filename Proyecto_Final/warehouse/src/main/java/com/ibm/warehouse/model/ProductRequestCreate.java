package com.ibm.warehouse.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestCreate {
    @NotNull
    @NotBlank
    private String name;

    @Min(1)
    private Long quantity;

    @NotNull
    private BigDecimal price;

    private String currency;


}
