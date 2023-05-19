package com.example.userdiscount.services.impl;

import com.example.userdiscount.domain.exceptions.DiscountNotFoundException;
import com.example.userdiscount.domain.exceptions.UserNotFoundException;
import com.example.userdiscount.domain.models.Discount;
import com.example.userdiscount.domain.models.DiscountID;
import com.example.userdiscount.domain.models.User;
import com.example.userdiscount.domain.models.UserID;
import com.example.userdiscount.domain.valueObjects.DateRange;
import com.example.userdiscount.domain.valueObjects.DiscountAmount;
import com.example.userdiscount.repository.DiscountRepository;
import com.example.userdiscount.repository.UserRepository;
import com.example.userdiscount.services.DiscountService;
import com.example.userdiscount.services.form.DiscountForm;
import com.example.userdiscount.services.form.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;
    private UserRepository userRepository;

    @Override
    public Discount findById(DiscountID id) {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @Override
    public Discount createDiscount(DiscountForm form) {
        Discount discount=new Discount(form.getStartDate(), form.getEndDate(), form.getDiscountAmount());
        discountRepository.save(discount);
        return discount;
    }

    @Override
    public void addUserToDiscount(DiscountID discountID, UserForm userForm) throws DiscountNotFoundException, UserNotFoundException {
        Discount discount = discountRepository.findById(discountID).orElseThrow(DiscountNotFoundException::new);
        User user = new User(userForm.getName(), userForm.getSurname(), userForm.getName(), userForm.getSurname());
        discount.addToUser(user);
        discountRepository.saveAndFlush(discount);
    }

    @Override
    public void removeUser(DiscountID discountID, UserID userID) throws DiscountNotFoundException, UserNotFoundException {
        Discount discount=discountRepository.findById(discountID).orElseThrow(DiscountNotFoundException::new);
        discount.removeUser(userID);
        discountRepository.saveAndFlush(discount);
    }

    @Override
    public boolean isDiscountCodeUsedByUser(DiscountID discountId, UserID userId) throws DiscountNotFoundException {
        Discount discount = discountRepository.findById(discountId).orElseThrow(DiscountNotFoundException::new);
        return discount.isDiscountCodeUsedByUser(userId);
    }


    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public List<User> users(DiscountID discountID) {
        Discount discount=discountRepository.findById(discountID).orElseThrow(DiscountNotFoundException::new);
        return discount.getUsers();
    }

    @Override
    public Discount discountCreated(DiscountID discountID, DiscountAmount discountAmount, UserID userID) {
        Discount discount=discountRepository.findById(discountID).orElseThrow(DiscountNotFoundException::new);
        User user=userRepository.findById(userID).orElseThrow(UserNotFoundException::new);
        discount.addUser(user);
        discountRepository.saveAndFlush(discount);
        return discount;
    }

}
