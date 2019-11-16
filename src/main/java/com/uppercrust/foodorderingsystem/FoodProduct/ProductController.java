package com.uppercrust.foodorderingsystem.FoodProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("category/{id}/products")
    public ResponseEntity<List<Product>> listProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.findAll(categoryId);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findOne(id);
        if (optionalProduct.isPresent())
            return ResponseEntity.ok().body(optionalProduct.get());
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findOne(id);
        if (optionalProduct.isPresent())
            productService.deleteProduct(id);
        else
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("category/{id}/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product, @PathVariable Long id) {
        Product newProduct = productService.save(product, id);
        if (newProduct != null) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newProduct.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(newProduct);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("category/{categoryId}/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Long id, @PathVariable(name = "categoryId") Long categoryId) {
        Optional<Product> optionalProduct = productService.findOne(id);
        if (optionalProduct.isPresent())
            return ResponseEntity.ok(productService.update(optionalProduct.get(), product));
        else
            return ResponseEntity.ok(productService.save(product,categoryId));
    }   //TODO 201 and 404 if null
}