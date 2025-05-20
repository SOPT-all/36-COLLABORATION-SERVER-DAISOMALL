package com.sopt.DaisoMall.domain.image.dto.request;

public record ProductImageUploadDto(
        long productId,
        boolean isMain // MAIN or DETAIL
) {
}
