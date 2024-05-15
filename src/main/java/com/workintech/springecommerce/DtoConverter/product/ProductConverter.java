package com.workintech.springecommerce.DtoConverter.product;

import com.workintech.springecommerce.dto.ProductResponse;
import com.workintech.springecommerce.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static ProductResponse toProductResponseConverter(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getImage(),
                product.getCategory().getId(),
                product.getPrice()
        );
    }

    public static List<ProductResponse> toProductResponseList(List<Product> products) {
        return products.stream()
                .map(ProductConverter::toProductResponseConverter)
                .collect(Collectors.toList());
    }
}
