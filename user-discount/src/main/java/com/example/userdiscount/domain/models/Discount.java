package com.example.userdiscount.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.userdiscount.domain.valueObjects.DateRange;
import com.example.userdiscount.domain.valueObjects.DiscountAmount;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "discount")
public class Discount extends AbstractEntity<DiscountID> {

    private DateRange dateCreated;
    private DateRange validUntil;
    private DiscountAmount discountAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<User> usersList;

    public Discount(DateRange dateCreated, DateRange validUntil, DiscountAmount discountAmount) {
        super(DiscountID.randomId(DiscountID.class));
        this.dateCreated=dateCreated;
        this.validUntil=validUntil;
        this.discountAmount=discountAmount;
    }

    public Discount() {

    }

    public void removeUser(UserID userID){
        this.usersList.removeIf(u->u.getId().equals(userID));
    }

    public boolean isDiscountCodeUsedByUser(UserID userId) {
        return usersList.stream().anyMatch(user -> user.getId().equals(userId));
    }

    public void addToUser(User user) {
        if (isDiscountCodeUsedByUser(user.getId())) {
            throw new IllegalArgumentException("The user has already used this discount code.");
        }
        usersList.add(user);
    }

    public void addUser(User user) {
        if (usersList == null) {
            usersList = new HashSet<>();
        }

        usersList.add(user);

    }

    public List<User> getUsers() {
        return new ArrayList<>(usersList);
    }
}
