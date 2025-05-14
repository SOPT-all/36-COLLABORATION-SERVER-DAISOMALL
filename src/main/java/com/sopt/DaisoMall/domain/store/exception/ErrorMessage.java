package com.sopt.DaisoMall.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    NOT_FOUND_PAGE("존재하지 않는 페이지입니다"),
    NOT_FOUND_SORT_OPTION("존재하지 않는 정렬 옵션입니다"),
    NOT_FOUND_BRAND ("존재하지 않는 브랜드입니다");

    private final String message;
}
