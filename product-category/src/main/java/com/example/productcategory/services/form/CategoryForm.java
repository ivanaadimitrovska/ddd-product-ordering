package com.example.productcategory.services.form;

import com.example.productcategory.domain.models.Product;
import com.example.sharedkernel.domain.financial.Currency;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class CategoryForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    List<Product> productList=new ArrayList<>();

    @NotNull
    private String categoryDescription;

    @NotNull
    private String categoryName;
}



