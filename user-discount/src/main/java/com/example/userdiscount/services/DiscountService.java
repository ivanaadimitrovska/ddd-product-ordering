package com.example.userdiscount.services;

import com.example.userdiscount.domain.exceptions.DiscountNotFoundException;
import com.example.userdiscount.domain.exceptions.UserNotFoundException;
import com.example.userdiscount.domain.models.Discount;
import com.example.userdiscount.domain.models.DiscountID;
import com.example.userdiscount.domain.models.User;
import com.example.userdiscount.domain.models.UserID;
import com.example.userdiscount.domain.valueObjects.DateRange;
import com.example.userdiscount.domain.valueObjects.DiscountAmount;
import com.example.userdiscount.services.form.DiscountForm;
import com.example.userdiscount.services.form.UserForm;

import java.util.List;

public interface DiscountService {

    Discount findById(DiscountID id);
    Discount createDiscount(DiscountForm form);
    void addUserToDiscount(DiscountID discountId, UserForm userForm) throws DiscountNotFoundException;
    void removeUser(DiscountID discountID, UserID userID) throws DiscountNotFoundException, UserNotFoundException;
    boolean isDiscountCodeUsedByUser(DiscountID discountId, UserID userId) throws DiscountNotFoundException;
    List<Discount> getAll();
    List<User> users(DiscountID discountID);
    Discount discountCreated(DiscountID discountID, DiscountAmount discountAmount, UserID userID);

    //void applyDiscount(DiscountID discountID, OrderID orderID) throws DiscountNotFoundException, OrderNotFoundException, DiscountCannotBeAppliedException;
}

