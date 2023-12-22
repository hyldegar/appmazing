package com.campusdual.appmazing.model.dao;

import com.campusdual.appmazing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByActiveTrue();
    List<Product> findByPriceGreaterThan(BigDecimal price);
    @Query(value = "SELECT * FROM PRODUCTS WHERE price > :price ORDER BY price ASC;", nativeQuery = true)
    List<Product> getProductsPriceBiggerThan(BigDecimal price);
}
