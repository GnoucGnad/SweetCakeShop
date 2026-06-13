package com.example.myapp.repository;

import com.example.myapp.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    List<CustomerOrder> findByCustomerIdOrderByOrderDateDesc(Integer customerId);
    List<CustomerOrder> findByStatus(String status);
}
