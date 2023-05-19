package com.example.userdiscount.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class DiscountID extends DomainObjectId {
    private DiscountID() {
        super(DiscountID.randomId(DiscountID.class).getId());
    }

    public DiscountID(@NonNull String uuid) {
        super(uuid);
    }
    public static DiscountID of(String uuid) {
        DiscountID p = new DiscountID(uuid);
        return p;
    }

}
