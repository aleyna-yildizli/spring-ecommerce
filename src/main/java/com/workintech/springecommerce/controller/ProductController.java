package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.DtoConverter.product.ProductConverter;
import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Product;
import com.workintech.springecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductResponse productResponse = ProductConverter.toProductResponseConverter(product);
        return ResponseEntity.ok(productResponse);
    }
}
