package com.example.userdiscount.services.form;

import com.example.userdiscount.domain.valueObjects.DateRange;
import com.example.userdiscount.domain.valueObjects.DiscountAmount;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DiscountForm {
    @NotNull
    private DateRange startDate;

    @NotNull
    private DateRange endDate;

    @NotNull
    private DiscountAmount discountAmount;
}

