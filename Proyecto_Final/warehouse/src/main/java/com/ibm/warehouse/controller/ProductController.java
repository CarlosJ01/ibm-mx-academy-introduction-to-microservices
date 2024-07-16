package com.ibm.warehouse.controller;

import com.ibm.warehouse.model.*;
import com.ibm.warehouse.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/stock/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductsResponse createNewProduct(@Valid @RequestBody ProductRequestCreate productRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        if (productRequest.getQuantity() == null)
            productRequest.setQuantity(Long.valueOf(1));
        if (productRequest.getCurrency() == null){
            productRequest.setCurrency("MXN");
        }
        else if (!productRequest.getCurrency().equals("MXN") && !productRequest.getCurrency().equals("USD")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        }
        Product product = productService.createOneProduct(productRequest);
        ProductsResponse response = new ProductsResponse();
        response.setProducts(product);
        return response;
    }

    @GetMapping("{productId}")
    public ProductResponse getProductById(@PathVariable UUID productId){
        Product data = productService.readOneById(productId);
        ProductResponse response = new ProductResponse();
        response.setProduct(data);
        return response;
    }

    @PutMapping("{productId}")
    public ProductsResponse updateProduct(
            @Valid @RequestBody ProductRequestUpdate productRequest,
            BindingResult bindingResult,
            @PathVariable UUID productId
    ){
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are not valid");
        if (productRequest.getCurrency() != null) {
            if (!productRequest.getCurrency().equals("MXN") && !productRequest.getCurrency().equals("USD")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are not valid");
            }
        }
        Product product = productService.UpdateOneProduct(productId, productRequest);
        ProductsResponse response = new ProductsResponse();
        response.setProducts(product);
        return response;
    }
}
