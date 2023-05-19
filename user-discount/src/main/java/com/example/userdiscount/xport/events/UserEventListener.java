//package com.example.userdiscount.xport.events;
//
//import com.example.sharedkernel.domain.config.TopicHolder;
//import com.example.sharedkernel.domain.events.DiscountCreated;
//import com.example.sharedkernel.domain.events.DomainEvent;
//import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
//import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
//import com.example.userdiscount.domain.models.DiscountID;
//import com.example.userdiscount.services.DiscountService;
//import lombok.AllArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class UserEventListener {
//
//    private final DiscountService discountService;
//
////    @KafkaListener(topics= TopicHolder.TOPIC_DISCOUNT_CREATED, groupId = "user-discount")
////    public void consumeOrderItemCreatedEvent(String jsonMessage) {
////        try {
////            DomainEvent event = DomainEvent.fromJson(jsonMessage, DiscountCreated.class);
////            discountService.discountCreated(DiscountID.of(event.ge()), event.ge());
////        } catch (Exception e){
////
////        }
////
////    }
////
////    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCategory")
////    public void consumeOrderItemRemovedEvent(String jsonMessage) {
////        try {
////            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
////            productService.orderItemRemoved(ProductID.of(event.getProductId()), event.getQuantity());
////        } catch (Exception e){
////
////        }
////
////    }
//}
