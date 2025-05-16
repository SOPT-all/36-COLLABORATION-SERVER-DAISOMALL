package com.sopt.DaisoMall.domain.image.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    INVALID_FILE_NAME("파일 이름이 유효하지 않습니다"),
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다");

    private final String message;
}
