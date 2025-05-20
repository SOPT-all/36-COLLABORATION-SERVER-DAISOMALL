package com.sopt.DaisoMall.domain.review.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    GET_ALL_REVIEW_SUCCESS("상품 리뷰 조회에 성공했습니다.");

    private final String message;
}
