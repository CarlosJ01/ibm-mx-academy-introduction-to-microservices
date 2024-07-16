package com.ibm.sales.service.Impl;

import com.ibm.sales.client.WarehouseClient;
import com.ibm.sales.model.*;
import com.ibm.sales.repository.OrderProductRepository;
import com.ibm.sales.repository.OrderRepository;
import com.ibm.sales.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    WarehouseClient warehouseClient;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, String currency) {

        final List<Product> products = new ArrayList<Product>();
        final BigDecimal[] total = {BigDecimal.valueOf(0)};

        request.getProducts().forEach(product -> {
            BigDecimal subtotal = BigDecimal.valueOf(0);
            
            ProductResponse response = warehouseClient.getProductById(product.getId());
            if (response.getProduct().getQuantity() < product.getQuantity()){
                throw new ResponseStatusException(
                        HttpStatus.NOT_ACCEPTABLE,
                        "Stock is not enough for this product %s".formatted(product.getId())
                );
            }

            if (response.getProduct().getCurrency().equals(currency)) {
                subtotal = subtotal.add(
                    response.getProduct().getPrice().multiply(
                            BigDecimal.valueOf(product.getQuantity())
                    )
                );
            } else {
                BigDecimal priceConverted = BigDecimal.valueOf(0);
                if (currency.equals("MXN") && response.getProduct().getCurrency().equals("USD")){
                    priceConverted = response.getProduct().getPrice().multiply(BigDecimal.valueOf(20));
                }

                if (currency.equals("USD") && response.getProduct().getCurrency().equals("MXN")){
                    priceConverted = response.getProduct().getPrice().divide(BigDecimal.valueOf(20));
                }
                response.getProduct().setCurrency(currency);
                response.getProduct().setPrice(priceConverted);
                subtotal = subtotal.add(priceConverted.multiply(BigDecimal.valueOf(product.getQuantity())));
            }
            response.getProduct().setQuantity(product.getQuantity());
            total[0] = total[0].add(subtotal);
            products.add(response.getProduct());
        });

        final Order newOrder = new Order();
        newOrder.setClient(request.getClient());
        newOrder.setTotal(total[0]);
        newOrder.setCurrency(currency);

        final Order order = orderRepository.saveAndFlush(newOrder);

        products.forEach(product -> {
           final OrderProduct newProduct = new OrderProduct();
           newProduct.setId(product.getId());
           newProduct.setName(product.getName());
           newProduct.setPrice(product.getPrice());
           newProduct.setCurrency(product.getCurrency());
           newProduct.setQuantity(product.getQuantity());
           newProduct.setOrderID(order.getId());
           orderProductRepository.saveAndFlush(newProduct);
        });

        final OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setClient(order.getClient());
        response.setProducts(products);
        response.setTotal(order.getTotal());
        response.setCurrency(order.getCurrency());

        return response;
    }

    @Override
    public OrderResponse readOrderById(UUID orderId, String currency) {
        final Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with %s not found".formatted(orderId))
        );

        return this.OrderToOrderResponse(order, currency);
    }

    @Override
    public List<OrderResponse> readOrdersByClient(String client, String currency) {
        List<Order> orders = orderRepository.getAllOrdersByClient(client);

        if (orders.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with client %s not found".formatted(client));

        return orders.stream().map(order -> OrderToOrderResponse(order, currency))
                .collect(Collectors.toList());
    }

    private OrderResponse OrderToOrderResponse(Order order, String currency) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setClient(order.getClient());
        response.setTotal(this.convertCurrency(order.getCurrency(), currency, order.getTotal()));
        response.setCurrency(currency);
        response.setProducts(this.getProductsByOrderId(order, currency));
        return response;
    }

    private List<Product> getProductsByOrderId(Order order, String currency) {
        final List<OrderProduct> productsRepository = orderProductRepository.getAllProductByOrderID(order.getId());
        final List<Product> products = productsRepository.stream().map(product -> {
            Product newProduct = new Product();
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setPrice(this.convertCurrency(order.getCurrency(), currency, product.getPrice()));
            newProduct.setCurrency(currency);
            newProduct.setQuantity(product.getQuantity());
            return newProduct;
        }).collect(Collectors.toList());
        return products;
    }

    private BigDecimal convertCurrency(String actual, String convert, BigDecimal price){
        if (!actual.equals(convert)) {
            if (convert.equals("MXN"))
                return price.multiply(BigDecimal.valueOf(20));
            if (convert.equals("USD"))
                return price.divide(BigDecimal.valueOf(20));
        }
        return price;
    }

}
