package com.uppercrust.foodorderingsystem.FoodCategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "FoodCategory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
   /* @OneToMany()
    private String product;*/
}
