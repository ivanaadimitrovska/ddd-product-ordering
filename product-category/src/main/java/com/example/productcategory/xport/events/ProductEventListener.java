package com.example.productcategory.xport.events;

import com.example.productcategory.domain.models.CategoryID;
import com.example.productcategory.domain.models.ProductID;
import com.example.productcategory.services.CategoryService;
import com.example.productcategory.services.form.CategoryForm;
import com.example.productcategory.services.impl.ProductService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {

//    private final CategoryService categoryService;
//
//    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCategory")
//    public void consumeOrderItemCreatedEvent(String jsonMessage) {
//        try {
//            OrderItemCreated event = DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
//            //CategoryForm categoryForm = null;
//            categoryService.orderItemCreated(CategoryID.of(event.getProductId()), event.getQuantity(), new CategoryForm(), ProductID.of(event.getProductId()));
//        } catch (Exception e){
//
//        }
//    }
//
//    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCategory")
//    public void consumeOrderItemRemovedEvent(String jsonMessage) {
//        try {
//            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
//            //CategoryForm categoryForm = null;
//            categoryService.orderItemRemoved(CategoryID.of(event.getProductId()), event.getQuantity(), new CategoryForm(), ProductID.of(event.getProductId()));
//        } catch (Exception e){
//
//
//        }
//    }
private final ProductService productService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCategory")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            productService.orderItemCreated(ProductID.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCategory")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            productService.orderItemRemoved(ProductID.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

}

