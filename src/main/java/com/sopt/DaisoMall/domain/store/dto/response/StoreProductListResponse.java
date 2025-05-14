package com.sopt.DaisoMall.domain.store.dto.response;

import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder
public record StoreProductListResponse(
        List<StoreProductResponse> products,
        PageableResponse pageable
) {
    public static StoreProductListResponse of(Slice<StoreProductResponse> slice) {
        return StoreProductListResponse.builder()
                .products(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
