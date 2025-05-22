package com.sopt.DaisoMall.domain.product.dto.response.list;

import com.sopt.DaisoMall.domain.product.dto.response.PopularProductResponse;
import java.util.List;

public record PopularProductListResponse(
        List<List<PopularProductResponse>> pages,
        int total
) {
}

