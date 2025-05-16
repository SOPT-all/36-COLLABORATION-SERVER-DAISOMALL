package com.sopt.DaisoMall.domain.store.dto.response;

import com.sopt.DaisoMall.domain.product.dto.response.PageableResponse;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder
public record StoreSearchListResponse(
        List<StoreSearchResponse> stores,
        PageableResponse pageable
) {
    public static StoreSearchListResponse of(Slice<StoreSearchResponse> slice) {
        return StoreSearchListResponse.builder()
                .stores(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
