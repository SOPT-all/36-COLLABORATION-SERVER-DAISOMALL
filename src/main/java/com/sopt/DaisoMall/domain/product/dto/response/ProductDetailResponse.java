package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.image.dto.request.ProductImageDto;
import com.sopt.DaisoMall.domain.product.dto.querydsl.QueryResultDto;

import java.util.List;

public record ProductDetailResponse(
        long productId,
        String productName,
        String price,
        String ratingAvg,
        String reviewCount,
        String brandName,
        ProductImageResponse productImages
) {
    public static ProductDetailResponse from(QueryResultDto dto, List<ProductImageDto> main, List<ProductImageDto> detail) {
        Double ratingAvg = Math.round(dto.ratingAvg() * 10.0) / 10.0;
        return new ProductDetailResponse(
                dto.productId(),
                dto.productName(),
                dto.price(),
                ratingAvg.toString(),
                dto.reviewCount().toString(),
                dto.brandName(),
                new ProductImageResponse(new ProductImageResponse.ProductImageWrapper(main, detail))
        );
    }

}
