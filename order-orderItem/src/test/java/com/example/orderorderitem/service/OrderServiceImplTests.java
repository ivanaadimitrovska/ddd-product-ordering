package com.example.orderorderitem.service;

import com.example.orderorderitem.domain.exceptions.OrderIdNotExistException;
import com.example.orderorderitem.domain.models.Order;
import com.example.orderorderitem.domain.models.OrderID;
import com.example.orderorderitem.domain.valueObjects.Product;
import com.example.orderorderitem.domain.valueObjects.ProductID;
import com.example.orderorderitem.services.OrderService;
import com.example.orderorderitem.services.forms.OrderForm;
import com.example.orderorderitem.services.forms.OrderItemForm;
import com.example.orderorderitem.services.xport.client.ProductClient;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;



    private static Product newProduct(String name, Money price) {
        Product p = new Product(ProductID.randomId(ProductID.class), name, price, 0, 0);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(newProduct("Pizza",Money.valueOf(Currency.MKD,1500)));
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(newProduct("Hot Dog",Money.valueOf(Currency.MKD,500)));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItemFormList(Arrays.asList(oi1,oi2));

        OrderID newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Product> productList = productClient.findAll();
        //System.out.println(productList);
        Product p1 = productList.get(0);
        Product p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItemFormList(Arrays.asList(oi1,oi2));

        OrderID newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}
