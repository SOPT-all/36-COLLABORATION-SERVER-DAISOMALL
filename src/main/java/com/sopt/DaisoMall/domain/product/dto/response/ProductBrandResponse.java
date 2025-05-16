package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import java.util.List;

public record ProductBrandResponse(
        Long productId,
        String productName,
        int price,
        List<String> tags
) {
    public static ProductBrandResponse from(Product product) {
        List<String> tagNames = product.getProductTagMappings().stream()
                .map(mapping -> mapping.getProductTag().getDisplayName())
                .toList();

        return new ProductBrandResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                tagNames
        );
    }
}
