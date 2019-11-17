package com.uppercrust.foodorderingsystem.FoodDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {

    List<Detail> findByProductId(Long productId);
}
