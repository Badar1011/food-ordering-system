package com.uppercrust.foodorderingsystem.FoodProduct;


import com.uppercrust.foodorderingsystem.FoodCategory.Category;
import com.uppercrust.foodorderingsystem.FoodCategory.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    public List<Product> findAll(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product, Long id) {

        Optional<Category> optionalCategory = categoryService.findOne(id);
        if (optionalCategory.isPresent()) {
            product.setCategory(optionalCategory.get());
            return productRepository.save(product);
        } else
            return null;

        //TODO error handling here for if category not found
    }

    public Product update(Product product, Product newProduct) {
        product.setDescription(newProduct.getDescription());
        product.setName(newProduct.getName());
        return save(product, product.getCategory().getId());
    }
}
