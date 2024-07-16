package com.ibm.products.services.impl;

import com.ibm.products.repository.ProductRepository;
import com.ibm.products.services.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public JSONObject findAll() {
        JSONObject response = new JSONObject();
        response.put("data", productRepository.findAll());
        response.put("code", HttpStatus.OK);
        return response;
    }

    @Override
    public void deletebyId(Long id) {
//        log.debug "{}", var
    final  var product = productRepository.findById(id)
            .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannont find product id %s".formatted(id))
            );
    productRepository.deleteById(id);
    }
}
