package com.example.userdiscount.xport.rest;

import com.example.userdiscount.domain.models.DiscountID;
import com.example.userdiscount.domain.models.User;
import com.example.userdiscount.services.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserResource {

    //    private final CategoryService products;
//
//    @GetMapping("/{id}")
//    public List<Product> getAll(@PathVariable String id) {
//        return products.products(CategoryID.of(String.valueOf(id)));
//    }
    private final DiscountService discountService;
    @GetMapping("/{id}")
    public List<User> getAll(@PathVariable String id) {
        return discountService.users(DiscountID.of(id));
    }

}
