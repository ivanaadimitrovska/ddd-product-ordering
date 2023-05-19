package com.example.orderorderitem.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class OrderItemID extends DomainObjectId {

    private OrderItemID() {
        super(OrderItemID.randomId(OrderItemID.class).getId());
    }

    public OrderItemID(String uuid) {
        super(uuid);
    }

}
