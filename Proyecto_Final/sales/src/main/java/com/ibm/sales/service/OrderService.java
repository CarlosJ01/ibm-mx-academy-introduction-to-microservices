package com.ibm.sales.service;

import com.ibm.sales.model.CreateOrderRequest;
import com.ibm.sales.model.CreateOrderResponse;
import com.ibm.sales.model.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request, String currency);

    OrderResponse readOrderById(UUID orderId, String currency);

    List<OrderResponse> readOrdersByClient(String client, String currency);
}
