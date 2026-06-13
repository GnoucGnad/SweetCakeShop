package com.example.myapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer reviewsId;

    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "customer_comment")
    private String customerComment;

    @Column(name = "rating")
    private Integer rating;

    // --- Getter và Setter ---
    public Integer getReviewsId() { return reviewsId; }
    public void setReviewsId(Integer reviewsId) { this.reviewsId = reviewsId; }

    public Integer getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(Integer orderDetailId) { this.orderDetailId = orderDetailId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getCustomerComment() { return customerComment; }
    public void setCustomerComment(String customerComment) { this.customerComment = customerComment; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
