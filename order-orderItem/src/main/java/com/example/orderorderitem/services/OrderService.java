package com.example.orderorderitem.services;

import com.example.orderorderitem.domain.exceptions.OrderIdNotExistException;
import com.example.orderorderitem.domain.exceptions.OrderItemIdNotExistException;
import com.example.orderorderitem.domain.models.Order;
import com.example.orderorderitem.domain.models.OrderID;
import com.example.orderorderitem.domain.models.OrderItemID;
import com.example.orderorderitem.domain.valueObjects.Address;
import com.example.orderorderitem.domain.valueObjects.User;
import com.example.orderorderitem.services.forms.OrderForm;
import com.example.orderorderitem.services.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderID placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderID orderID);
    void addItem(OrderID orderID, OrderItemForm orderItemForm) throws OrderIdNotExistException;
    void deleteItem(OrderID orderID, OrderItemID orderItemID) throws OrderIdNotExistException, OrderItemIdNotExistException;
    boolean idUserLoggedIn(OrderID orderID, OrderForm orderForm, User user, Address address) throws OrderIdNotExistException;
    void cancelOrder(OrderID orderId);
}
