package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;

public record PopularProductResponse(
        Long productId,
        String productName,
        String productCode,
        int price,
        String imageUrl
) {
    public static PopularProductResponse from(Product product){
        return new PopularProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getPrice(),
                product.getImages().stream()
                        .filter(ProductImage::isMain)
                        .map(ProductImage::getImageUrl)
                        .findFirst()
                        .orElse("")
        );
    }
}
