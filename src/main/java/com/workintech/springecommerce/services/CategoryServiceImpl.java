package com.workintech.springecommerce.services;

import com.workintech.springecommerce.DtoConverter.category.CategoryConverter;
import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.entity.Category;
import com.workintech.springecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return new CategoryConverter().toCategoryResponseListConverter(categories);
    }
}
