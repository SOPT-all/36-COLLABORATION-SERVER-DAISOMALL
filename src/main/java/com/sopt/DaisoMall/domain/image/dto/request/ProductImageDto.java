package com.sopt.DaisoMall.domain.image.dto.request;

public record ProductImageDto(
        long productImageId,
        String imageUrl,
        int sortOrder
) {
}
