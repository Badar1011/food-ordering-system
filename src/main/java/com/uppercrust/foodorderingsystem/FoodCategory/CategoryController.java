package com.uppercrust.foodorderingsystem.FoodCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        Category newCategory = categoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(location).body(newCategory);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.findOne(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findOne(id);
        if (optionalCategory.isPresent())
            categoryService.deleteOne(id);
        else
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid Category newCategory) {
        Optional<Category> optionalCategory = categoryService.findOne(id);
        if (optionalCategory.isPresent())
            return ResponseEntity.ok(categoryService.update(optionalCategory.get(), newCategory));
        else    //TODO pass the location back and use 201
            return ResponseEntity.ok(categoryService.save(newCategory));
    }
}