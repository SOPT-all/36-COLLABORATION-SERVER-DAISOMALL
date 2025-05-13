package com.sopt.DaisoMall.domain.product.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    BEAUTY_HYGIENE("뷰티/위생"),
    KITCHENWARE("주방용품"),
    CLEANING_BATHROOM("청소/화장실"),
    STORAGE_ORGANIZATION("정리/수납");

    private final String displayName;
}
