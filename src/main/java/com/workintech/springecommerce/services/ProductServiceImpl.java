package com.workintech.springecommerce.services;

import com.workintech.springecommerce.DtoConverter.product.ProductConverter;
import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Product;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductConverter.toProductResponseList(products);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() ->
                new EcommerceException("Product with given id is not exist: " + id, HttpStatus.NOT_FOUND));
    }
}
