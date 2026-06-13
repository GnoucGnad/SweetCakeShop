package com.example.myapp.repository;

import com.example.myapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByOptionCakeId(Integer optionCakeId);
    List<Product> findByCakeNameContainingIgnoreCase(String keyword);
    List<Product> findByQuantityGreaterThan(Integer quantity);
}