package com.example.productcategory.services;

import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.models.ProductID;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> findAll();
    Product orderItemCreated(ProductID productId, int quantity);
    Product orderItemRemoved(ProductID productId, int quantity);
}
