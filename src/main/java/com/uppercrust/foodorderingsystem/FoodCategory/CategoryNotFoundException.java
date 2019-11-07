package com.uppercrust.foodorderingsystem.FoodCategory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CategoryNotFoundException extends RuntimeException {

    CategoryNotFoundException(Long id){
        super("Could not find category" + id);
    }
}
