package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.DomainObjectId;


import javax.persistence.Embeddable;

//import javax.persistence.Embeddable;

@Embeddable
public class UserID extends DomainObjectId {
    protected UserID() {
        super(ProductID.randomId(ProductID.class).getId());
    }
    public UserID(String uuid) {
        super(uuid);
    }
}
