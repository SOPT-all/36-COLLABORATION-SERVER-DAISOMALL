package com.sopt.DaisoMall.domain.store.dto.response;

import com.sopt.DaisoMall.domain.product.dto.response.PageableResponse;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;
@Builder
public record StoreStockListResponse(
        List<StoreStockResponse> stores,
        PageableResponse pageable
) {
    public static StoreStockListResponse of(Slice<StoreStockResponse> slice) {
        return StoreStockListResponse.builder()
                .stores(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
