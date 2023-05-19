package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Product implements ValueObject {

    private final ProductID id;
    private final String name;
    private final Money price;
    private final int sales;
    private final int quantity;

    private Product() {
        this.id=ProductID.randomId(ProductID.class);
        this.name= "";
        this.price = Money.valueOf(Currency.MKD,0);
        this.sales = 0;
        this.quantity=0;
    }

    @JsonCreator
    public Product(@JsonProperty("id") ProductID id,
                   @JsonProperty("productName") String name,
                   @JsonProperty("productPrice") Money price,
                   @JsonProperty("sales") int sales,
                   @JsonProperty("productQuantity") int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.quantity=quantity;
    }
}


