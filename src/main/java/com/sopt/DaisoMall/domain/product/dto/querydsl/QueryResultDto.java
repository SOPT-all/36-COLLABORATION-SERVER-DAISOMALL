package com.sopt.DaisoMall.domain.product.dto.querydsl;

public record QueryResultDto(
        Long productId,
        String productName,
        String price,
        Double ratingAvg,
        Long reviewCount,
        String brandName
) {
}
