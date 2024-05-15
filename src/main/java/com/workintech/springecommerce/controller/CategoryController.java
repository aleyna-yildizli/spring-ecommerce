package com.workintech.springecommerce.controller;


import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
@CrossOrigin("http://localhost:5173")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }
}
