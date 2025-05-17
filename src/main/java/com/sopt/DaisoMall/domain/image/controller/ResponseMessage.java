package com.sopt.DaisoMall.domain.image.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    S3_UPLOAD_SUCCESS("S3에 이미지 업로드 성공"),
    S3_FILE_DELETE_SUCCESS("S3에 있는 상품 이미지 삭제 성공");

    private final String message;
}
