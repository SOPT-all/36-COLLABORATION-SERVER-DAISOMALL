package com.sopt.DaisoMall.domain.product.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductCategoryResponse(
        long productId,
        String productName,
        String mainImage,
        int price,
        String category,
        List<String> tags
) {

    // 쿼리에서 사용할 생성자
    public ProductCategoryResponse(long productId, String productName, String mainImage, int price, String category) {
        this(productId, productName, mainImage, price, category, List.of());
    }

    // 태그를 나중에 추가할 수 있는 setter-like method
    public ProductCategoryResponse withTags(List<String> tags) {
        return new ProductCategoryResponse(this.productId, this.productName, this.mainImage, this.price, this.category, tags);
    }
}
