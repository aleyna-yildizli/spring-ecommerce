package com.workintech.springecommerce.services;

import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    Product findById(Long id);
}
