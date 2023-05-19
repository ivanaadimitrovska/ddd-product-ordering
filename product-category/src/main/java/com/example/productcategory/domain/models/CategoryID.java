package com.example.productcategory.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class CategoryID extends DomainObjectId {
    private CategoryID() {
        super(CategoryID.randomId(CategoryID.class).getId());
    }

    public CategoryID(@NonNull String uuid) {
        super(uuid);
    }

    public static CategoryID of(String uuid) {
        CategoryID p = new CategoryID(uuid);
        return p;
    }

}
