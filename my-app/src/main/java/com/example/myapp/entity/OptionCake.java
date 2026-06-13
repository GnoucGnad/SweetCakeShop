package com.example.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "optioncake")
public class OptionCake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_cake_id")
    private Integer optionCakeId;

    @Column(name = "category_name")
    private String categoryName;

    // --- Getter và Setter ---
    public Integer getOptionCakeId() { return optionCakeId; }
    public void setOptionCakeId(Integer optionCakeId) { this.optionCakeId = optionCakeId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
