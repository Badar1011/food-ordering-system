package com.uppercrust.foodorderingsystem.FoodProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
