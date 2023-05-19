package com.example.userdiscount.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class DiscountAmount implements ValueObject {

        private int amount;
        private Currency currency;

        public DiscountAmount(int amount) {
            this.amount = amount;
        }

    protected DiscountAmount() {
        this.amount=0;
        this.currency=null;
    }

    public double calculateDiscount(Money money) {
        double discountRate = 0.1;
        double discountAmount = money.getAmount() * discountRate;

        return discountAmount;
    }


    public Money apply(Money money) {
        Money originalValue = Money.valueOf(money.getCurrency(), money.getAmount());
        double discount = calculateDiscount(originalValue);
        Money discountedValue = originalValue.subtract(Money.valueOf(money.getCurrency(), (int) discount));
        return discountedValue;
    }

}


