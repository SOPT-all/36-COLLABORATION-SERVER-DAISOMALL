package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;

public record ProductResponse(
        Long productId,
        String productName,
        String productCode,
        int price,
        String mainImage
) {
    public static ProductResponse from(Product product) {
        String imageUrl = product.getProductImages().stream()
                .filter(image -> image.isMain() && image.getSortOrder() == 0)
                .map(ProductImage::getImageUrl)
                .findFirst()
                .orElse(null);

        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getPrice(),
                imageUrl
        );
    }
}
