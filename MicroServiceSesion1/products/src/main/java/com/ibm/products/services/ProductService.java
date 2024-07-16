package com.ibm.products.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    public JSONObject findAll();

    void deletebyId(Long id);
}
