package com.sopt.DaisoMall.global.common.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    JSON_PARSE_ERROR("잘못된 JSON 형식의 요청입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
