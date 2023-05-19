package com.example.productcategory;

import ch.qos.logback.core.pattern.Converter;
import com.example.productcategory.domain.models.CategoryID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductCategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCategoryApplication.class, args);
    }

//    @Bean
//    public Converter<String, CategoryID> stringToCategoryIDConverter() {
//        return new StringToCategoryIDConverter();
//    }
}
