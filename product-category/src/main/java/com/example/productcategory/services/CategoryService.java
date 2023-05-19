package com.example.productcategory.services;

import com.example.productcategory.domain.exceptions.CategoryIdNotExistException;
import com.example.productcategory.domain.exceptions.ProductNotFoundException;
import com.example.productcategory.domain.models.Category;
import com.example.productcategory.domain.models.CategoryID;
import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.models.ProductID;
import com.example.productcategory.services.form.CategoryForm;
import com.example.productcategory.services.form.ProductForm;

import java.util.List;

public interface CategoryService {
    Category findById(CategoryID categoryID);
    Category createCategory(CategoryForm form);
    Category orderItemCreated(CategoryID categoryID, int quantity, CategoryForm categoryForm, ProductID productID);
    Category orderItemRemoved(CategoryID categoryID, int quantity, CategoryForm categoryForm, ProductID productID);
    void addProducts(CategoryID categoryID, ProductForm productForm) throws CategoryIdNotExistException;
    void deleteProduct(CategoryID categoryID, ProductID productID) throws CategoryIdNotExistException, ProductNotFoundException;
    List<Category> getAll();

    // orderItemCreated(CategoryID , int , String );
    List<Product> products(CategoryID categoryId);
}
