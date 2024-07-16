package com.ibm.warehouse.service.implementation;

import com.ibm.warehouse.model.Product;
import com.ibm.warehouse.model.ProductRequestCreate;
import com.ibm.warehouse.model.ProductRequestUpdate;
import com.ibm.warehouse.repository.ProductRepository;
import com.ibm.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product readOneById(UUID id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with %s not found".formatted(id))
        );
    }

    @Override
    public Product createOneProduct(ProductRequestCreate productRequestCreate) {
        final Product newProduct = new Product();
        newProduct.setName(productRequestCreate.getName());
        newProduct.setPrice(productRequestCreate.getPrice());
        newProduct.setCurrency(productRequestCreate.getCurrency());
        newProduct.setQuantity(productRequestCreate.getQuantity());
        return productRepository.saveAndFlush(newProduct);
    }

    @Override
    public Product UpdateOneProduct(UUID productId, ProductRequestUpdate productRequest) {
        final Product product = this.readOneById(productId);
        if (productRequest.getName() != null)
            product.setName(productRequest.getName());
        if (productRequest.getQuantity() != null)
            product.setQuantity(productRequest.getQuantity());
        if (productRequest.getPrice() != null)
            product.setPrice(productRequest.getPrice());
        if (productRequest.getCurrency() != null)
            product.setCurrency(productRequest.getCurrency());
        return productRepository.saveAndFlush(product);
    }

}
