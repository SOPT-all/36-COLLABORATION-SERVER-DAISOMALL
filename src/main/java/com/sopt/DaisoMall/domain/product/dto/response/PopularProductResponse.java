package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;

public record PopularProductResponse(
        Long productId,
        String productName,
        String productCode,
        int price,
        String mainImage
) {
    public static PopularProductResponse from(Product product){
        String imageUrl = product.getProductImages().stream()
                .filter(image -> image.isMain() && image.getSortOrder() == 0)
                .map(ProductImage::getImageUrl)
                .findFirst()
                .orElse(null);

        return new PopularProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getPrice(),
                imageUrl
        );
    }
}
