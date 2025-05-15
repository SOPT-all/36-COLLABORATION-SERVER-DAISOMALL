package com.sopt.DaisoMall.domain.product.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreType {
    FRANCHISE("가맹점"),
    DISTRIBUTION("유통점");

    private final String displayName;
}
