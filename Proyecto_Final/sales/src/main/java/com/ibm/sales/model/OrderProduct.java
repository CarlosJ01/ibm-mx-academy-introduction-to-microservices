package com.ibm.sales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name="orderProductTable")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idOrderProduct;
    private UUID orderID;
    private UUID id;
    private String name;
    private BigDecimal price;
    private String currency;
    private Long quantity;
}
