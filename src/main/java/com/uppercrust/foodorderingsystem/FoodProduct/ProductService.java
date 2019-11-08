package com.uppercrust.foodorderingsystem.FoodProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long id)
    {
        return productRepository.findById(id);
    }

    public void deleteOne(Product product){
        productRepository.delete(product);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
}
