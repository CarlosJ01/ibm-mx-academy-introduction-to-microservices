package com.ibm.sales.model;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestUpdate {
    private String name;

    @Min(1)
    private Long quantity;

    private BigDecimal price;

    private String currency;


}
