package com.sopt.DaisoMall.domain.store.dto;

public record StoreFilterRequest(
        Boolean pickup, // 픽업가능매장
        Boolean excludeOutOfStock, // 품절 제외
        Boolean excludeStoreType // 가맹/유통점 제외
) {
}
