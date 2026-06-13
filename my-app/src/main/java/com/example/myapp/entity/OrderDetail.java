package com.example.myapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "cake_id")
    private Integer cakeId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    // --- Getter và Setter ---
    public Integer getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(Integer orderDetailId) { this.orderDetailId = orderDetailId; }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getCakeId() { return cakeId; }
    public void setCakeId(Integer cakeId) { this.cakeId = cakeId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
}
