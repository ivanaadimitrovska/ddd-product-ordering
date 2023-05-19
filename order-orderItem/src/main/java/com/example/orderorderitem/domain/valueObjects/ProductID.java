package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.DomainObjectId;
//import jakarta.persistence.Embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductID extends DomainObjectId {
    protected ProductID() {
        super(ProductID.randomId(ProductID.class).getId());
    }
    public ProductID(String uuid) {
        super(uuid);
    }

}
