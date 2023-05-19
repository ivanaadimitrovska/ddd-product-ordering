package com.example.orderorderitem.domain.models;

import com.example.orderorderitem.domain.valueObjects.ProductID;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.financial.Money;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemID> {

    private Money itemPrice;

    @Column(nullable = false )
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name="product_id", nullable = false))
    private ProductID productID;

    public OrderItem() {

    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }

    public OrderItem(@NonNull ProductID productID,  Money itemPrice, @NonNull int quantity){
        super(DomainObjectId.randomId(OrderItemID.class));
        this.productID = productID;
        this.itemPrice = itemPrice;
        this.quantity = quantity;

    }
}

