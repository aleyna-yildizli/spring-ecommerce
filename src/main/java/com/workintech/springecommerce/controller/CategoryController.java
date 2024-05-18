package com.workintech.springecommerce.controller;


import com.workintech.springecommerce.DtoConverter.category.CategoryConverter;
import com.workintech.springecommerce.DtoConverter.product.ProductConverter;
import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Category;
import com.workintech.springecommerce.entity.Product;
import com.workintech.springecommerce.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAllCategories();
    }

}
