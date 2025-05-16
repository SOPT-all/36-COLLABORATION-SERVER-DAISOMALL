package com.sopt.DaisoMall.domain.product.dto.querydsl;

import java.util.List;

public record QueryResultDto(
        Long productId,
        String productName,
        String price,
        Double ratingAvg,
        Long reviewCount,
        String brandName,
        List<String> mainImages,
        List<String> detailImages
) {
}
