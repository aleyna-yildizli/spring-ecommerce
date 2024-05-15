package com.workintech.springecommerce.dto;

public record ProductResponse(Long id, String name, String description, Double rating, Integer stock, Integer sellCount, String image,
                              Long category_id, Double price) {
}
