package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;

public record ProductResponse(
        Long productId,
        String productName,
        String productCode,
        int price
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getPrice()
        );
    }
}
