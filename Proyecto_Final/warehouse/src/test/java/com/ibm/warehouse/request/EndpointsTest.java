package com.ibm.warehouse.request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.warehouse.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetByIdSusscess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                "http://localhost:8080/stock/v1/product/3702b220-1d29-457d-b0be-f2cc6a6a2ac1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetByIdError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "http://localhost:8080/stock/v1/product/3702b220-1d29-457d-b0be-f2cc6a6a2ac9")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testCreateSuccess() throws Exception{
        final Product product = new Product();
        product.setName("RTX 3090");
        product.setPrice(BigDecimal.valueOf(63759));
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "http://localhost:8080/stock/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testCreateError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "http://localhost:8080/stock/v1/product")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testUpdateSuccess() throws Exception{
        final Product product = new Product();
        product.setName("Nvidia RTX 3090");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setCurrency("USD");
        product.setQuantity(500L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "http://localhost:8080/stock/v1/product/3702b220-1d29-457d-b0be-f2cc6a6a2ac1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateError() throws Exception{
        final Product product = new Product();
        product.setCurrency("EUR");
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "http://localhost:8080/stock/v1/product/3702b220-1d29-457d-b0be-f2cc6a6a2ac1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
