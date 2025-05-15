package com.sopt.DaisoMall.domain.product.dto.response;

import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder
public record ProductListResponse(
        List<ProductResponse> products,
        PageableResponse pageable
) {
    public static ProductListResponse of(Slice<ProductResponse> slice) {
        return ProductListResponse.builder()
                .products(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
