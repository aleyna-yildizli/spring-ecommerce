package com.workintech.springecommerce.services;

import com.workintech.springecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
}
