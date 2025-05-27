package com.sopt.DaisoMall.domain.product.entity.enums;


import com.sopt.DaisoMall.domain.product.exception.CategoryNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {
    BEAUTY_HYGIENE("뷰티/위생"),
    KITCHENWARE("주방용품"),
    CLEANING_BATHROOM("청소/화장실"),
    STORAGE_ORGANIZATION("정리/수납");

    private final String displayName;

    public static Category from(String value) {
        return Arrays.stream(values())
                .filter(option ->
                        option.name().equalsIgnoreCase(value) ||
                                option.getDisplayName().equals(value)
                )
                .findAny()
                .orElseThrow(CategoryNotFoundException::new);
    }
}
