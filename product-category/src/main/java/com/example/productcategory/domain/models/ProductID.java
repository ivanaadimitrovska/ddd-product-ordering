package com.example.productcategory.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ProductID extends DomainObjectId {

    private ProductID() {
        super(ProductID.randomId(ProductID.class).getId());
    }

    public ProductID(@NonNull String uuid) {
        super(uuid);
    }

    public static ProductID of(String uuid) {
        ProductID p = new ProductID(uuid);
        return p;
    }

}
