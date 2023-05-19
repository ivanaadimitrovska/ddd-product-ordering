package com.example.productcategory.services.impl;

import com.example.productcategory.domain.exceptions.CategoryIdNotExistException;
import com.example.productcategory.domain.exceptions.ProductNotFoundException;
import com.example.productcategory.domain.exceptions.ProductOutOfStockException;
import com.example.productcategory.domain.models.Category;
import com.example.productcategory.domain.models.CategoryID;
import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.models.ProductID;
import com.example.productcategory.repository.CategoryRepository;
import com.example.productcategory.services.CategoryService;
import com.example.productcategory.services.form.CategoryForm;
import com.example.productcategory.services.form.ProductForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(CategoryID id) {
        return categoryRepository.findById(id).orElseThrow(CategoryIdNotExistException::new);
    }

    @Override
    public Category createCategory(CategoryForm form) {
        Category category=new Category(form.getCategoryDescription(), form.getCategoryName());
        categoryRepository.save(category);
        return category;
    }



    @Override
    public Category orderItemCreated(CategoryID categoryID, int quantity, CategoryForm categoryForm, ProductID productID) {
        Category category=categoryRepository.findById(categoryID).orElseThrow(CategoryIdNotExistException::new);
        Product product= (Product) categoryForm.getProductList().stream().map(p->p.getId().equals(productID));
        category.addSales(quantity, product);
        categoryRepository.saveAndFlush(category);
        return category;
    }

    @Override
    public Category orderItemRemoved(CategoryID categoryID, int quantity, CategoryForm categoryForm, ProductID productID) {
        Category category=categoryRepository.findById(categoryID).orElseThrow(CategoryIdNotExistException::new);
        Product product= (Product) categoryForm.getProductList().stream().map(p->p.getId().equals(productID));
        category.removeSales(quantity, product);
        categoryRepository.saveAndFlush(category);
        return category;
    }


    @Override
    public void addProducts(CategoryID categoryID, ProductForm productForm) throws CategoryIdNotExistException {
        Category category = categoryRepository.findById(categoryID).orElseThrow(CategoryIdNotExistException::new);
        Product product = Product.build(productForm.getProductName(), productForm.getPrice(), productForm.getSales(), productForm.getQuantity());

        if (product.isInStock()) {
            Product product1=Product.build(productForm.getProductName(), productForm.getPrice(), productForm.getSales(), productForm.getQuantity());
            category.addProduct(product1);
            categoryRepository.saveAndFlush(category);
        } else {
            throw new ProductOutOfStockException("Product " + productForm.getProductName() + " is out of stock.");
        }
    }

    @Override
    public void deleteProduct(CategoryID categoryID, ProductID productID) throws CategoryIdNotExistException, ProductNotFoundException {
        Category category=categoryRepository.findById(categoryID).orElseThrow(CategoryIdNotExistException::new);
        category.removeProduct(productID);
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> products(CategoryID categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(CategoryIdNotExistException::new);
        return category.getProducts();
    }
//
//    public CategoryID convert(Long source) {
//        // Implement your conversion logic here
//        // Create a new CategoryID object using the provided Long value and return it
//        return new CategoryID(source);
//    }
}
