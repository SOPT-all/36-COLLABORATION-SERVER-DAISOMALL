package com.sopt.DaisoMall.domain.product.dto.response;

import java.util.List;

public record ProductCategoryResponse(
        long productId,
        String productName,
        String mainImage,
        int price,
        String category,
        List<String> tags
) {
}
