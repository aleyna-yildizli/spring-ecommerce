package com.workintech.springecommerce.DtoConverter.category;

import com.workintech.springecommerce.dto.CategoryResponse;
import com.workintech.springecommerce.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter {
    public CategoryResponse toCategoryResponseConverter(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getCode(),
                category.getGender(),
                category.getTitle(),
                category.getRating(),
                category.getImg()
        );
    }

    public List<CategoryResponse> toCategoryResponseListConverter(List<Category> categories) {
        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(new CategoryResponse(
                    category.getId(),
                    category.getCode(),
                    category.getGender(),
                    category.getTitle(),
                    category.getRating(),
                    category.getImg()
            ));
        }
        return responses;
    }
}
