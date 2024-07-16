package com.ibm.sales.client;

import com.ibm.sales.model.ProductRequestUpdate;
import com.ibm.sales.model.ProductResponse;
import com.ibm.sales.model.ProductsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "WAREHOUSE-SERVICE", path = "/stock/v1/product")
public interface WarehouseClient {

    @GetMapping("{productId}")
    public ProductResponse getProductById(@PathVariable UUID productId);

    @PutMapping("{productId}")
    public ProductsResponse updateProduct(@RequestBody ProductRequestUpdate productRequest, @PathVariable UUID productId);
}
