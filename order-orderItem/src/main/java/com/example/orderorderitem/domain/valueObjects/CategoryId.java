package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.DomainObjectId;


import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends DomainObjectId {
    protected CategoryId() {
        super(CategoryId.randomId(CategoryId.class).getId());
    }
    public CategoryId(String uuid) {
        super(uuid);
    }
}
