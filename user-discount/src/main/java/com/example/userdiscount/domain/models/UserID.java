package com.example.userdiscount.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class UserID extends DomainObjectId {
    private UserID() {
        super(UserID.randomId(UserID.class).getId());
    }

    public UserID(@NonNull String uuid) {
        super(uuid);
    }

    public static UserID of(String uuid) {
        UserID p = new UserID(uuid);
        return p;
    }

}
