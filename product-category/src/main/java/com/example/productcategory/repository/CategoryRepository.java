package com.example.productcategory.repository;

import com.example.productcategory.domain.models.Category;
import com.example.productcategory.domain.models.CategoryID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryID> {
}
