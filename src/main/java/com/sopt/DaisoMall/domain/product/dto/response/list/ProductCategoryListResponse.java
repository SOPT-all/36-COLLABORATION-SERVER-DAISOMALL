package com.sopt.DaisoMall.domain.product.dto.response.list;

import com.sopt.DaisoMall.domain.product.dto.response.PageableResponse;
import com.sopt.DaisoMall.domain.product.dto.response.ProductCategoryResponse;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;
@Builder
public record ProductCategoryListResponse(
        List<ProductCategoryResponse> products,
        PageableResponse pageable
) {
    public static ProductCategoryListResponse of(Slice<ProductCategoryResponse> slice){
        return ProductCategoryListResponse.builder()
                .products(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}