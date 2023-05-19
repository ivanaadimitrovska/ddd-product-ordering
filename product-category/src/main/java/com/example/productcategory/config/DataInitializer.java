package com.example.productcategory.config;

import com.example.productcategory.domain.models.Category;
import com.example.productcategory.domain.models.Product;
import com.example.productcategory.domain.valueObjects.Ingredient;
import com.example.productcategory.repository.CategoryRepository;
import com.example.productcategory.repository.ProductRepository;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initData() {
        Product p4= new Product("Pizza", Money.valueOf(Currency.MKD,500), 10, 100, new Ingredient("chocolate", 100));
        Product p1 = new Product("Pizza", Money.valueOf(Currency.MKD,500), 10, 100, new Ingredient("hazelnuts", 80));
        Product p3=new Product("iva", Money.valueOf(Currency.MKD,400), 34, 12, new Ingredient("almonds", 100));
        Product p2 = new Product("Ice Cream", Money.valueOf(Currency.MKD,100), 5, 200, new Ingredient("raisin", 100));
        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(Arrays.asList(p4, p1, p2, p3));
        }

        Category c1=new Category("Chokoladni", "So belo chokolado");
        c1.addProduct(p1);
        c1.addProduct(p2);
        c1.addProduct(p3);
        categoryRepository.save(c1);
    }


}
//    @PostConstruct
//    public void initData() {
//        Product p4= new Product("Pizza", Money.valueOf(Currency.MKD,500), 10, 100,
//                List.of(new Ingredient("chocolate", 100), new Ingredient("chocolate", 100)));
//        Product p1 = new Product("Pizza", Money.valueOf(Currency.MKD,500),
//                10, 100, List.of(new Ingredient("chocolate", 100), new Ingredient("chocolate", 100)));
//        Product p3=new Product("iva", Money.valueOf(Currency.MKD,400), 34,
//                12, List.of(new Ingredient("almonds", 100), new Ingredient("milk", 100)));
//        Product p2 = new Product("Ice Cream", Money.valueOf(Currency.MKD,100),
//                5, 200, List.of(new Ingredient("raisin", 100), new Ingredient("ketchup", 100)));
//        if (productRepository.findAll().isEmpty()) {
//            productRepository.saveAll(Arrays.asList(p4, p1, p2, p3));
//        }
//    }