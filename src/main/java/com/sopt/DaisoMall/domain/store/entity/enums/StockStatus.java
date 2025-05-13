package com.sopt.DaisoMall.domain.store.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockStatus {
    IN_STOCK("픽업 가능"),
    OUT_OF_STOCK("재고 소진"),
    COMING_SOON("입고 예정");

    private final String displayName;
}
