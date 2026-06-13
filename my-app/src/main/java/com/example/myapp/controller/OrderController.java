package com.example.myapp.controller;

import com.example.myapp.dto.OrderRequest;
import com.example.myapp.entity.*;
import com.example.myapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private CustomerOrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private ProductRepository productRepository;

    // Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // 1. Tìm hoặc tạo khách hàng
            Customer customer = customerRepository.findByPhoneNumber(request.getPhone())
                    .orElseGet(() -> {
                        Customer newCustomer = new Customer();
                        newCustomer.setFirstName(request.getFirstName());
                        newCustomer.setLastName(request.getLastName());
                        newCustomer.setPhoneNumber(request.getPhone());
                        newCustomer.setCustomerEmail(request.getEmail());
                        newCustomer.setAddressId(request.getAddress());
                        return customerRepository.save(newCustomer);
                    });

            // 2. Tạo đơn hàng
            CustomerOrder order = new CustomerOrder();
            order.setCustomerId(customer.getCustomerId());
            order.setStatus("pending");
            order.setOrderDate(LocalDateTime.now());
            order.setTotalPrice(BigDecimal.ZERO);
            order = orderRepository.save(order);

            // 3. Tạo chi tiết đơn hàng
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (OrderRequest.OrderItemRequest item : request.getItems()) {
                Product product = productRepository.findById(item.getCakeId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

                OrderDetail detail = new OrderDetail();
                detail.setOrderId(order.getOrderId());
                detail.setCakeId(item.getCakeId());
                detail.setQuantity(item.getQuantity());

                BigDecimal subTotal = BigDecimal.valueOf(product.getPrice() * item.getQuantity());
                detail.setSubTotal(subTotal);
                orderDetailRepository.save(detail);

                totalPrice = totalPrice.add(subTotal);
            }

            // 4. Cập nhật tổng tiền
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);

            // 5. Tạo thanh toán
            Payment payment = new Payment();
            payment.setOrderId(order.getOrderId());
            payment.setPaymentMethod(request.getPaymentMethod() != null ? request.getPaymentMethod() : "cash");
            payment.setPaymentStatus("pending");
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepository.save(payment);

            // 6. Trả về kết quả
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Đặt hàng thành công!");
            response.put("orderId", order.getOrderId());
            response.put("totalPrice", totalPrice);
            response.put("status", order.getStatus());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Lấy danh sách đơn hàng theo customer ID
    @GetMapping("/customer/{customerId}")
    public List<CustomerOrder> getOrdersByCustomer(@PathVariable Integer customerId) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId);
    }

    // Lấy chi tiết đơn hàng
    @GetMapping("/{orderId}/details")
    public List<OrderDetail> getOrderDetails(@PathVariable Integer orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
