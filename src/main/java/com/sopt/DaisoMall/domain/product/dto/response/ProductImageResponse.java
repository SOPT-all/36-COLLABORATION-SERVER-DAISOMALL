package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.image.dto.request.ProductImageDto;

import java.util.List;

public record ProductImageResponse(
        List<ProductImageDto> main,
        List<ProductImageDto> detail
) {
}
