package com.example.myapp.controller;

import com.example.myapp.entity.Product;
import com.example.myapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Lấy tất cả sản phẩm
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
    }

    // Lấy sản phẩm theo danh mục (1=Sweet, 2=Salty)
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        return productRepository.findByOptionCakeId(categoryId);
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productRepository.findByCakeNameContainingIgnoreCase(keyword);
    }

    // Lấy sản phẩm còn hàng
    @GetMapping("/available")
    public List<Product> getAvailableProducts() {
        return productRepository.findByQuantityGreaterThan(0);
    }
}