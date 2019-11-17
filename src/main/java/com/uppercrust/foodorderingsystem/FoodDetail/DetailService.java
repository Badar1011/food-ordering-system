package com.uppercrust.foodorderingsystem.FoodDetail;

import com.uppercrust.foodorderingsystem.FoodCategory.Category;
import com.uppercrust.foodorderingsystem.FoodProduct.Product;
import com.uppercrust.foodorderingsystem.FoodProduct.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {


    private DetailRepository detailRepository;
    private ProductService productService;

    @Autowired
    public DetailService(DetailRepository detailRepository, ProductService productService) {
        this.detailRepository = detailRepository;
        this.productService = productService;
    }

    public List<Detail> findAll(Long productId) {
        Optional<Product> optionalProduct = productService.findOne(productId);
        if (optionalProduct.isPresent())
            return detailRepository.findByProductId(productId);
        return null;
    }

    public Optional<Detail> findOne(Long id) {
        return detailRepository.findById(id);
    }

    public Detail save(Detail detail, Long productId) {
        Optional<Product> optionalProduct = productService.findOne(productId);
        if (optionalProduct.isPresent()) {
            detail.setProduct(optionalProduct.get());
            return detailRepository.save(detail);
        } else
            return null;
    }

    public void deleteOne(Long id) {
        detailRepository.deleteById(id);
    }

    public Detail update(Detail detail, Detail newDetail) {
        detail.setName(newDetail.getName());
        detail.setPrice(newDetail.getPrice());
        return save(detail, detail.getProduct().getId());
    }

}