package com.example.productcategory.domain.models;

import com.example.productcategory.domain.exceptions.ProductOutOfStockException;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;

import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity<CategoryID> {

    private String categoryDescription;
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Product> productsList;

//    public Category(String categoryName, String categoryDescription, List<Product> list) {
//        super(CategoryID.randomId(CategoryID.class));
//        this.categoryName="";
//        this.categoryDescription="";
//        this.productsList= (Set<Product>) list;
//    }


    public Category() {

    }

    public Category(String categoryName, String categoryDescription, List<Product> list) {
        super(CategoryID.randomId(CategoryID.class));
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.productsList = new HashSet<>(list);
    }


    public Category(String categoryDescription, String categoryName) {
        super(CategoryID.randomId(CategoryID.class));
        this.categoryName=categoryName;
        this.categoryDescription=categoryDescription;
    }

    public void removeProduct(ProductID productID){
        productsList.removeIf(p->p.getId().equals(productID));
    }

    public Product addSales(int quantity, Product product){
        product.addSales(quantity);
        return product;
    }

    public Product removeSales(int quantity, Product product){
        product.removeSales(quantity);
        return product;
    }

//    public void addProduct(String productName, Money price, int sales, int quantity) {
//        if (productsList == null) {
//            productsList = new HashSet<>();
//        }
//
//        var product = Product.build(productName, price, sales, quantity);
//        if (product.isInStock()) {
//            productsList.add(product);
//        } else {
//            throw new ProductOutOfStockException("Product " + product.getProductName() + " is out of stock.");
//        }
//    }

    public void addProduct(Product product) {
        if (productsList == null) {
            productsList = new HashSet<>();
        }
        //var Product = new Product(product);
        if (product.isInStock()) {
            productsList.add(product);
        } else {
            throw new ProductOutOfStockException("Product " + product.getProductName() + " is out of stock.");
        }

        //productsList.add(product);

    }

//    public List<Product> getProducts(){
//        return (List<Product>) productsList;
//    }

    public List<Product> getProducts() {
        return new ArrayList<>(productsList);
    }

//    public void addProduct(String productName, Money price, int sales, int quantity) {
//        var product = Product.build(productName, price, sales, quantity);
//        if (product.isInStock()) {
//            productsList.add(product);
//        } else {
//            throw new ProductOutOfStockException("Product " + product.getProductName() + " is out of stock.");
//        }
//    }
}
