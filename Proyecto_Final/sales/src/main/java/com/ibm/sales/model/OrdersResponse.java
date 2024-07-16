package com.ibm.sales.model;

import lombok.Data;

import java.util.List;

@Data
public class OrdersResponse {
    List<OrderResponse> orders;
}
