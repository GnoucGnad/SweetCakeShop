package com.example.myapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cake_id")
    private Integer cakeId;

    @Column(name = "option_cake_id")
    private Integer optionCakeId;

    @Column(name = "cake_name")
    private String cakeName;

    private String description;
    private Double price;
    private Integer quantity;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "option_cake_id", insertable = false, updatable = false)
    private OptionCake optionCake;

    // --- Getter và Setter ---
    public Integer getCakeId() { return cakeId; }
    public void setCakeId(Integer cakeId) { this.cakeId = cakeId; }

    public Integer getOptionCakeId() { return optionCakeId; }
    public void setOptionCakeId(Integer optionCakeId) { this.optionCakeId = optionCakeId; }

    public String getCakeName() { return cakeName; }
    public void setCakeName(String cakeName) { this.cakeName = cakeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getCreateAt() { return createAt; }
    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }

    public OptionCake getOptionCake() { return optionCake; }
    public void setOptionCake(OptionCake optionCake) { this.optionCake = optionCake; }
}