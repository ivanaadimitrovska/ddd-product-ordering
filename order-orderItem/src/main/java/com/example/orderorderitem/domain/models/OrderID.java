package com.example.orderorderitem.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class OrderID extends DomainObjectId {

    private OrderID() {
        super(OrderID.randomId(OrderID.class).getId());
    }

    public OrderID(@NonNull String uuid) {
        super(uuid);
    }

}
