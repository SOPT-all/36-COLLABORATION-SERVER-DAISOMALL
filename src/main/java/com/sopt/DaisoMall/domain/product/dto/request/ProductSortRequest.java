package com.sopt.DaisoMall.domain.product.dto.request;

import com.sopt.DaisoMall.domain.product.entity.enums.SortOption;

public record ProductSortRequest(
        String sortOption,
        int pageNumber,
        int pageSize
) {
    public SortOption toSortOption() {
        return SortOption.from(sortOption);
    }
}
