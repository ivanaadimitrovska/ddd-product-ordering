package com.example.orderorderitem.domain.models;


import com.example.orderorderitem.domain.valueObjects.Address;
import com.example.orderorderitem.domain.valueObjects.Product;
import com.example.orderorderitem.domain.valueObjects.User;
import com.example.orderorderitem.domain.valueObjects.UserID;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderID> {

    private Instant orderDate;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name = "order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Money total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> OrderItemList;

    private Address address;

    @AttributeOverride(name = "id", column = @Column(name="user_id", nullable = false))
    private UserID userID;

    public Order(Instant now, @NotNull Currency currency) {
        super(OrderID.randomId(OrderID.class));
        this.orderDate=now;
        this.currency=currency;
    }

    public Order() {
            super(OrderID.randomId(OrderID.class));
            this.OrderItemList = new HashSet<>();
    }

    public Money total(){
        return OrderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

//    public OrderItem addItem(@NonNull Product product, int quantity){
//        Objects.requireNonNull(product, "product must not be null");
//        var item=new OrderItem(product.getId(), product.getPrice(), quantity);
//        OrderItemList.add(item);
//        System.out.println(OrderItemList);
//        System.out.println(item);
//        return item;
//    }

    public OrderItem addItem(@NonNull Product product, int quantity) {
        Objects.requireNonNull(product, "Product must not be null");

        if (OrderItemList == null) {
            OrderItemList = new HashSet<>();
        }

        var item = new OrderItem(product.getId(), product.getPrice(), quantity);
        OrderItemList.add(item);

        return item;
    }
//    public OrderItem addItem(@NonNull Product product, int qty) {
//        Objects.requireNonNull(product,"product must not be null");
//        var item  = new OrderItem(product.getId(),product.getPrice(),qty);
//        orderItemList.add(item);
//        return item;
//    }
//
//    public void removeItem(@NonNull OrderItemId orderItemId) {
//        Objects.requireNonNull(orderItemId,"Order Item must not be null");
//        orderItemList.removeIf(v->v.getId().equals(orderItemId));
//    }

    public void removeItem(@NonNull OrderItemID orderItemID){
        Objects.requireNonNull(orderItemID, "Order Item must not be null");
        OrderItemList.removeIf(v -> v.getId().equals(orderItemID));
    }

    public boolean isValid(Address address){
        return address.validate();
    }

    public OrderState getOrderState() {
        return this.orderState;
    }

    public void updateOrderState(OrderState newOrderState) {
        if (this.orderState == OrderState.PROCESSED && newOrderState != OrderState.CANCELLED) {
            throw new IllegalArgumentException("Invalid order state transition.");
        }
        this.orderState = newOrderState;
    }
}

