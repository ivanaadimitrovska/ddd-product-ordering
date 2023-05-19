package com.example.userdiscount.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
public class User extends AbstractEntity<UserID> {

    private String username;
    private String password;
    private String name;
    private String surname;

    public User(String name, String surname, String username, String password) {
        super(UserID.randomId(UserID.class));
        this.username=username;
        this.name=name;
        this.surname=surname;
        this.password=password;
    }

    public User() {

    }
}
