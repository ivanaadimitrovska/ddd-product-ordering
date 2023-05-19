package com.example.orderorderitem.services.forms;

import com.example.orderorderitem.domain.valueObjects.Address;
import com.example.orderorderitem.domain.valueObjects.User;
import com.example.sharedkernel.domain.financial.Currency;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    List<OrderItemForm> itemFormList=new ArrayList<>();

    @Column(nullable = true)
    private User user;

    @Column(nullable = true)
    private Address address;
}

//    @NotNull
//    private Currency currency;
//
//    @Valid
//    @NotEmpty
//    private List<OrderItemForm> items = new ArrayList<>();


