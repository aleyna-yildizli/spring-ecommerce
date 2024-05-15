package com.workintech.springecommerce.services;

import com.workintech.springecommerce.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

}
