package com.example.orderorderitem.services.forms;

import com.example.orderorderitem.domain.valueObjects.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {

    @NotNull
    private Product product;

    @Min(1)
    private int quantity;
}
