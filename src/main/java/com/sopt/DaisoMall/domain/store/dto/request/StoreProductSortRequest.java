package com.sopt.DaisoMall.domain.store.dto.request;

import com.sopt.DaisoMall.domain.store.entity.enums.SortOption;

public record StoreProductSortRequest(
        SortOption sortOption,
        int pageNumber,
        int pageSize
) {
}
