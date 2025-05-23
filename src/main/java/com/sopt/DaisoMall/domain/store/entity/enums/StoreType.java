package com.sopt.DaisoMall.domain.store.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreType {
    FRANCHISE("가맹점"),
    DISTRIBUTION("유통점"),
    DIRECT("본사직영점");

    private final String displayName;
}
