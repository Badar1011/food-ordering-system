package com.uppercrust.foodorderingsystem.FoodProduct;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uppercrust.foodorderingsystem.FoodCategory.Category;
import com.uppercrust.foodorderingsystem.FoodDetail.Detail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {


    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Detail> detail;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;
}
