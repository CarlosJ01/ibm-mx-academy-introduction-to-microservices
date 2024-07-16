package com.ibm.sales.request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sales.controller.OrderController;
import com.ibm.sales.model.CreateOrderRequest;
import com.ibm.sales.model.Order;
import com.ibm.sales.model.ProductOrderRequest;
import com.ibm.sales.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testCreateOrderSuccess() throws Exception{
        final CreateOrderRequest request = new CreateOrderRequest();
        request.setClient("Gabriel Castillo");

        final List<ProductOrderRequest> products = new ArrayList<>();
        ProductOrderRequest product = new ProductOrderRequest();
        product.setId(UUID.fromString("3702b220-1d29-457d-b0be-f2cc6a6a2ac1"));
        product.setQuantity(10L);
        products.add(product);

        product = new ProductOrderRequest();
        product.setId(UUID.fromString("3702b220-1d29-457d-b0be-f2cc6a6a2ac5"));
        product.setQuantity(100L);
        products.add(product);

        request.setProducts(products);

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "http://localhost:8081/sales/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testCreateOrderError400() throws Exception{
        final CreateOrderRequest request = new CreateOrderRequest();
        request.setClient("Gabriel Castillo");
        request.setProducts(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "http://localhost:8081/sales/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testCreateOrderError406() throws Exception{
        final CreateOrderRequest request = new CreateOrderRequest();
        request.setClient("Gabriel Castillo");

        final List<ProductOrderRequest> products = new ArrayList<>();
        ProductOrderRequest product = new ProductOrderRequest();
        product.setId(UUID.fromString("3702b220-1d29-457d-b0be-f2cc6a6a2ac1"));
        product.setQuantity(100000000L);
        products.add(product);

        request.setProducts(products);
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "http://localhost:8081/sales/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotAcceptable())
                .andReturn();
    }

    @Test
    public void testGetByIdSuccess() throws Exception{
        Order order = new Order();
        order.setClient("TEST");
        order.setCurrency("MXN");
        order.setTotal(BigDecimal.valueOf(1000));
        order = orderRepository.saveAndFlush(order);

        mockMvc.perform(MockMvcRequestBuilders.get(
                                "http://localhost:8081/sales/v1/order/%s".formatted(order.getId()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetByIdError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                "http://localhost:8080/stock/v1/product/d08c3270-4546-441d-b5cb-55e032812345")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetByNameSucces() throws Exception{
        Order order = new Order();
        order.setClient("TEST");
        order.setCurrency("MXN");
        order.setTotal(BigDecimal.valueOf(1000));
        order = orderRepository.saveAndFlush(order);

        mockMvc.perform(MockMvcRequestBuilders.get(
                                "http://localhost:8081/sales/v1/order?client=%s".formatted(order.getClient()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetByNameError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "http://localhost:8081/sales/v1/order?client=123")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
