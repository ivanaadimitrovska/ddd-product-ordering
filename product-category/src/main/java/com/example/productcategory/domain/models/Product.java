package com.example.productcategory.domain.models;

import com.example.productcategory.domain.valueObjects.Ingredient;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "product")
@Getter
public class Product extends AbstractEntity<ProductID> {

    private String productName;
    private int productQuantity;
    private int sales;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money productPrice;

    //@OneToMany
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name_of_ingredient")),
            @AttributeOverride(name = "quantity", column = @Column(name = "quantity_of_ingredient"))
    })
    private Ingredient ingredients_candy;

    public Product(String productName, Money productPrice, int sales, int productQuantity, Ingredient ingredients_candy){
        super(ProductID.randomId(ProductID.class));
        this.productName=productName;
        this.sales=sales;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
        this.ingredients_candy= ingredients_candy;
    }

    public Product() {

    }

//    public Product Product(Product product) {
//        Product Product=new Product(product);
////        Product.productName=this.productName;
////        product.productPrice=productPrice;
////        product.sales=sales;
////        product.productQuantity=productQuantity;
//        return Product;
//    }


    public static Product build(String productName, Money productPrice, int sales, int productQuantity){
        Product product=new Product();
        product.productName=productName;
        product.productPrice=productPrice;
        product.sales=sales;
        product.productQuantity=productQuantity;
        return product;
    }


    public void addSales(int quantity){
        this.sales=this.sales+quantity;
    }

    public void removeSales(int quantity){
        this.sales=this.sales-quantity;
    }

    public boolean isInStock() {
        return productQuantity > 0;
    }
}