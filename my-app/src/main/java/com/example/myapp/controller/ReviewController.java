package com.example.myapp.controller;

import com.example.myapp.entity.Review;
import com.example.myapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // Lấy đánh giá theo sản phẩm
    @GetMapping("/product/{cakeId}")
    public List<Review> getReviewsByProduct(@PathVariable Integer cakeId) {
        return reviewRepository.findByCakeId(cakeId);
    }

    // Lấy tất cả đánh giá
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
