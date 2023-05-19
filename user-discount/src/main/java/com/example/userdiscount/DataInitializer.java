package com.example.userdiscount;

import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import com.example.userdiscount.domain.models.Discount;
import com.example.userdiscount.domain.models.User;
import com.example.userdiscount.domain.valueObjects.DateRange;
import com.example.userdiscount.domain.valueObjects.DiscountAmount;
import com.example.userdiscount.repository.DiscountRepository;
import com.example.userdiscount.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;

    @PostConstruct
    public void initData() throws ParseException {
        User u1= new User("petko","petkovski" , "petko.petkovski", "petko5");
        User u2 = new User("nikola", "nikolovski", "nikola.nikolovski", "nikola4");
        User u3=new User("iva", "ivanovska", "ivan.ivanovska", "iva3");

        if (userRepository.findAll().isEmpty()) {
            userRepository.saveAll(Arrays.asList(u1,u2,u3));
        }


        String startDateString = "2021-05-15";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateCreated = new Date();
        startDateCreated = dateFormat.parse(startDateString);
        String endDateString = "2021-05-15";
        Date endDateCreated = new Date();
        endDateCreated = dateFormat.parse(endDateString);

        String startDateString1 = "2021-05-15";
        Date startDateCreated1 = new Date();
        startDateCreated1 = dateFormat.parse(startDateString);
        String endDateString1 = "2021-05-15";
        Date endDateCreated1 = new Date();
        endDateCreated1 = dateFormat.parse(endDateString);
        DateRange dateCreated = new DateRange(startDateCreated, endDateCreated);
        DateRange validUntil = new DateRange(startDateCreated1, endDateCreated1);


        DiscountAmount discountAmount = new DiscountAmount(10);


        Discount discount = new Discount(dateCreated, validUntil, discountAmount);
        Discount d1=new Discount(dateCreated, validUntil, discountAmount);
        d1.addUser(u1);
        d1.addUser(u2);
        d1.addUser(u3);
        discountRepository.save(d1);
    }


}
