package com.example.orderorderitem.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address implements ValueObject {
        private String street;
        private String city;
        private String state;
        private String zipCode;

    protected Address() {
        this.street = null;
        this.city = null;
        this.state = null;
        this.zipCode = null;
    }

    public Address(@NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zipCode){
        this.street=street;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
    }

    public boolean validate() throws IllegalArgumentException {
            if (street == null || street.isEmpty()) {
                throw new IllegalArgumentException("Street is required.");
            }

            if (city == null || city.isEmpty()) {
                throw new IllegalArgumentException("City is required.");
            }

            if (state == null || state.isEmpty()) {
                throw new IllegalArgumentException("State is required.");
            }

            if (zipCode == null || zipCode.isEmpty()) {
                throw new IllegalArgumentException("Zip code is required.");
            }
        return true;
    }

}

