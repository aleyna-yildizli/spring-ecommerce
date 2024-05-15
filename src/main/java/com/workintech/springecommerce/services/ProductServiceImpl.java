package com.workintech.springecommerce.services;

import com.workintech.springecommerce.DtoConverter.product.ProductConverter;
import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Product;
import com.workintech.springecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductConverter.toProductResponseList(products);
    }
}
