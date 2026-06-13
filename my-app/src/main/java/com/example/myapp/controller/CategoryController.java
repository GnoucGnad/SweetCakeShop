package com.example.myapp.controller;

import com.example.myapp.entity.OptionCake;
import com.example.myapp.repository.OptionCakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private OptionCakeRepository optionCakeRepository;

    @GetMapping
    public List<OptionCake> getAllCategories() {
        return optionCakeRepository.findAll();
    }
}
