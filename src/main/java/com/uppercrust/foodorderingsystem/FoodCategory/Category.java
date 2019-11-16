package com.uppercrust.foodorderingsystem.FoodCategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uppercrust.foodorderingsystem.FoodProduct.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Product> product;
}
