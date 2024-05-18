package com.workintech.springecommerce.services;

import com.workintech.springecommerce.DtoConverter.category.CategoryConverter;
import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.entity.Category;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new CategoryConverter().toCategoryResponseListConverter(categories);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElseThrow(() ->
                new EcommerceException("Category with given id is not exist: " + id, HttpStatus.NOT_FOUND));
    }
}
