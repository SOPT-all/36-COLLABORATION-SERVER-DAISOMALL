package com.sopt.DaisoMall.domain.image.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    PRODUCT("products"),
    REVIEW("reviews");

    private final String displayName;
}
