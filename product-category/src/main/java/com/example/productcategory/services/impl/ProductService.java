package com.example.productcategory.services.impl;

import com.example.productcategory.domain.exceptions.ProductNotFoundException;
import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.models.ProductID;
import com.example.productcategory.repository.ProductRepository;
import com.example.productcategory.services.ProductServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product orderItemCreated(ProductID productId, int quantity) {
        Product product=productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.addSales(quantity);
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product orderItemRemoved(ProductID productId, int quantity) {
        Product product=productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.removeSales(quantity);
        productRepository.saveAndFlush(product);
        return product;
    }
}
