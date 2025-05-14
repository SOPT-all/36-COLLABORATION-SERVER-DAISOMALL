package com.sopt.DaisoMall.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    NOT_FOUND_BRAND ("존재하지 않는 브랜드입니다");

    private final String message;
}
