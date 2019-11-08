package com.uppercrust.foodorderingsystem.FoodProduct;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {


    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;

    //private Details details;
}
