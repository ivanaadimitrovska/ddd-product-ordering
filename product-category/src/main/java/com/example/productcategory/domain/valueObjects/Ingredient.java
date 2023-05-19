package com.example.productcategory.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Ingredient implements ValueObject {

    private String name;
    private int quantity;

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Ingredient() {
        this.name=null;
        this.quantity=0;
    }
}

