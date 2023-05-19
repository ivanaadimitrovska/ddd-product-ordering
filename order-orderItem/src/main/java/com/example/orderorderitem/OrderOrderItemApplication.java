package com.example.orderorderitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.orderorderitem", "com.example.sharedkernel"})
public class OrderOrderItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderOrderItemApplication.class, args);
    }

}
