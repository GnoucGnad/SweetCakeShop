package com.example.myapp.repository;

import com.example.myapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByOrderDetailId(Integer orderDetailId);

    @Query("SELECT r FROM Review r JOIN OrderDetail od ON r.orderDetailId = od.orderDetailId WHERE od.cakeId = :cakeId AND r.rating IS NOT NULL")
    List<Review> findByCakeId(Integer cakeId);
}
