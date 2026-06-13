package com.example.myapp.dto;

import java.util.List;

public class OrderRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String paymentMethod;
    private List<OrderItemRequest> items;

    // --- Inner class cho từng sản phẩm trong đơn ---
    public static class OrderItemRequest {
        private Integer cakeId;
        private Integer quantity;

        public Integer getCakeId() { return cakeId; }
        public void setCakeId(Integer cakeId) { this.cakeId = cakeId; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    // --- Getter và Setter ---
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
