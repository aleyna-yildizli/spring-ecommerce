package com.workintech.springecommerce.services;

import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();

    Category findById(Long id);
}
