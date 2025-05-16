package com.sopt.DaisoMall.domain.store.dto.response;

import com.querydsl.core.annotations.QueryProjection;

public record StoreSearchResponse(
        Long storeId,
        String storeName,
        String location,
        String openingHours,
        String storeType,
        long stockCount,
        int shelfNo,
        String floor
) {
    @QueryProjection
    public StoreSearchResponse {
    }
}
