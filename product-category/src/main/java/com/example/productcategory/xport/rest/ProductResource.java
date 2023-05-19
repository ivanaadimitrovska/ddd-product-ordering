package com.example.productcategory.xport.rest;

import com.example.productcategory.domain.models.Category;
import com.example.productcategory.domain.models.CategoryID;
import com.example.productcategory.domain.models.Product;
import com.example.productcategory.services.CategoryService;
import com.example.productcategory.services.ProductServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {

    private final CategoryService products;

    @GetMapping("/{id}")
    public List<Product> getAll(@PathVariable String id) {
        return products.products(CategoryID.of(String.valueOf(id)));
    }
//    private final ProductServiceInterface productServiceInterface;
//    @GetMapping
//    public List<Product> getAll() {
//        return productServiceInterface.findAll();
//    }

}
