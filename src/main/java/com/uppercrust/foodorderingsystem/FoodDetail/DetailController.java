package com.uppercrust.foodorderingsystem.FoodDetail;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DetailController {


    private DetailService detailService;


    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/products/{productId}/details")
    public ResponseEntity<List<Detail>> listDetailsByProductId(@PathVariable Long productId) {
        List<Detail> detailList = detailService.findAll(productId);
        return ResponseEntity.ok().body(detailList);
    }

    @GetMapping("details/{id}")
    public ResponseEntity<Detail> getDetail(@PathVariable Long id) {
        Optional<Detail> optionalDetail = detailService.findOne(id);
        if (optionalDetail.isPresent())
            return ResponseEntity.ok(optionalDetail.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/products/{productId}/details")
    public ResponseEntity<Detail> newDetail(@RequestBody @Valid Detail detail, @PathVariable Long productId) {
        Detail newDetail = detailService.save(detail, productId);
        if (newDetail != null) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newDetail.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(newDetail);
        } else
            return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetail(@PathVariable Long id) {
        Optional<Detail> optionalDetail = detailService.findOne(id);
        if (optionalDetail.isPresent())
            detailService.deleteOne(id);
        else
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("products/{productId}/details/{id}")
    public ResponseEntity<Detail> updateDetail(@RequestBody @Valid Detail detail, @PathVariable Long id, @PathVariable Long productId) {
        Optional<Detail> optionalDetail = detailService.findOne(id);
        if (optionalDetail.isPresent())
            return ResponseEntity.ok(detailService.update(optionalDetail.get(), detail));
        else
            return ResponseEntity.ok(detailService.save(detail, productId));
    }

}