package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class User implements ValueObject {

    private final UserID userID;
    private final String username;
    private final String password;
    private final String name;
    private final String surname;

    private User() {
        this.userID=UserID.randomId(UserID.class);
        this.username= "";
        this.password= "";
        this.name= "";
        this.surname= "";
    }

    @JsonCreator
    public User(UserID userID, String username, String password, String name, String surname){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
