package com.example.orderorderitem.services.impl;

import com.example.orderorderitem.domain.exceptions.OrderCannotBeCanceledException;
import com.example.orderorderitem.domain.exceptions.OrderIdNotExistException;
import com.example.orderorderitem.domain.exceptions.OrderItemIdNotExistException;
import com.example.orderorderitem.domain.models.Order;
import com.example.orderorderitem.domain.models.OrderID;
import com.example.orderorderitem.domain.models.OrderItemID;
import com.example.orderorderitem.domain.models.OrderState;
import com.example.orderorderitem.domain.valueObjects.Address;
import com.example.orderorderitem.domain.valueObjects.User;
import com.example.orderorderitem.repository.OrderRepository;
import com.example.orderorderitem.services.OrderService;
import com.example.orderorderitem.services.forms.OrderForm;
import com.example.orderorderitem.services.forms.OrderItemForm;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.financial.Money;
import com.example.sharedkernel.domain.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.xml.transform.Source;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;



        @Override
    public OrderID placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null");
        var constraintViolations=validator.validate(orderForm);
        if(constraintViolations.size()>0){
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder=orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }



    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderID orderID) {
        return orderRepository.findById(orderID);
    }

    @Override
    public void addItem(OrderID orderID, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order=orderRepository.findById(orderID).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getProduct(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderID orderID, OrderItemID orderItemID) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order=orderRepository.findById(orderID).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemID);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public boolean idUserLoggedIn(OrderID orderID, OrderForm orderForm, User user, Address address) throws OrderIdNotExistException {
        Order order=orderRepository.findById(orderID).orElseThrow(OrderIdNotExistException::new);
        return orderForm.getUser().getUsername().equals(user.getUsername()) &&
                orderForm.getUser().getPassword().equals(user.getPassword()) &&
                orderForm.getUser().getUserID().equals(user.getUserID()) &&
                order.isValid(address);
    }

    private Order toDomainObject(OrderForm orderForm){
        var order=new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItemFormList().forEach(item->order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }

//    private Order toDomainObject(OrderForm orderForm) {
//        var order = new Order(Instant.now(),orderForm.getCurrency());
//        orderForm.getItems().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
//        return order;
//    }


    @Override
    public void cancelOrder(OrderID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderIdNotExistException::new);

        if (order.getOrderState() == OrderState.PROCESSED) {
            throw new OrderCannotBeCanceledException("Order cannot be canceled as it has already been shipped.");
        }

        order.updateOrderState(OrderState.CANCELLED);
        orderRepository.save(order);
    }
}
