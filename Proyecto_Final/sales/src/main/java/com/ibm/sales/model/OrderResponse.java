package com.ibm.sales.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponse {
    private UUID id;
    private String client;
    private List<Product> products;
    private BigDecimal total;
    private String currency;
}
