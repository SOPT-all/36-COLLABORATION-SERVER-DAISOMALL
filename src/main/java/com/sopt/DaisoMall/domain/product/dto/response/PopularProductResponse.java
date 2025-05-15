package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;

public record PopularProductResponse(
        Long productId,
        String productName,
        String productCode,
        int price
) {
    public static PopularProductResponse from(Product product){
        return new PopularProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getPrice()
        );
    }
}
