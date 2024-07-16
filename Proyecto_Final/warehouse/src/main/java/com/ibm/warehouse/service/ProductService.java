package com.ibm.warehouse.service;

import com.ibm.warehouse.model.Product;
import com.ibm.warehouse.model.ProductRequestCreate;
import com.ibm.warehouse.model.ProductRequestUpdate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductService {
    Product readOneById(UUID id);

    Product createOneProduct(ProductRequestCreate productRequestCreate);

    Product UpdateOneProduct(UUID productId, ProductRequestUpdate productRequest);
}
