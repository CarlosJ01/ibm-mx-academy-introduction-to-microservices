package com.ibm.sales.controller;

import com.ibm.sales.model.CreateOrderRequest;
import com.ibm.sales.model.CreateOrderResponse;
import com.ibm.sales.model.OrderResponse;
import com.ibm.sales.model.OrdersResponse;
import com.ibm.sales.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Currency", defaultValue = "MXN") String currency
    ) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        request.getProducts().forEach(product -> {
            if (product == null || product.getId() == null || product.getQuantity() == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
            if (product.getQuantity() < 1)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        });
        if (!currency.equals("MXN") && !currency.equals("USD"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");

        OrderResponse order = orderService.createOrder(request, currency);
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrder(order);

        return response;
    }


    @GetMapping("{orderId}")
    public CreateOrderResponse getOrderById(
            @PathVariable UUID orderId,
            @RequestHeader(value = "Currency", defaultValue = "MXN") String currency
    ) {
        if (!currency.equals("MXN") && !currency.equals("USD"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        OrderResponse order = orderService.readOrderById(orderId, currency);
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrder(order);
        return response;
    }

    @GetMapping()
    public OrdersResponse getOrderByClient(
            @RequestParam String client,
            @RequestHeader(value = "Currency", defaultValue = "MXN") String currency
    ) {
        if (!currency.equals("MXN") && !currency.equals("USD"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        List<OrderResponse> orders = orderService.readOrdersByClient(client, currency);
        OrdersResponse response = new OrdersResponse();
        response.setOrders(orders);
        return response;
    }
}
