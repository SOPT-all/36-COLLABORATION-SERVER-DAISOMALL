package com.sopt.DaisoMall.domain.product.dto.response.list;

import com.sopt.DaisoMall.domain.product.dto.response.PageableResponse;
import com.sopt.DaisoMall.domain.product.dto.response.ProductBrandResponse;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder
public record ProductBrandListResponse(
        List<ProductBrandResponse> products,
        PageableResponse pageable
) {
    public static ProductBrandListResponse of(Slice<ProductBrandResponse> slice) {
        return ProductBrandListResponse.builder()
                .products(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
