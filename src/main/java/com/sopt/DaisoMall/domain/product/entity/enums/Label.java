package com.sopt.DaisoMall.domain.product.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Label {
    HOT("지금 추천 상품"),
    NEW("신상품");

    private final String displayName;
}
