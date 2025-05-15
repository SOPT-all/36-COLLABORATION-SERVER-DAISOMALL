package com.sopt.DaisoMall.domain.store.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    GET_STORE_STOCKS_SUCCESS("해당 상품에 대한 매장 재고 리스트입니다."),
    STORE_STOCKS_FILTERING_SUCCESS("필터링에 성공하였습니다.");

    private final String message;
}
