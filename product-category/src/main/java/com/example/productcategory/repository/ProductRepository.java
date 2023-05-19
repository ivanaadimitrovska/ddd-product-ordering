package com.example.productcategory.repository;

import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.models.ProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductID> {
}
