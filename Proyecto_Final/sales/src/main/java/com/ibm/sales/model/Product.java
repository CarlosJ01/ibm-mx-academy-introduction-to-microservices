package com.ibm.sales.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String currency;
    private Long quantity;
}
