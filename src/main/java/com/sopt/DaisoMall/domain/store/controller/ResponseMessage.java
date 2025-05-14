package com.sopt.DaisoMall.domain.store.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SEARCH_STORE_PRODUCTS_SUCCESS("매장 상품 검색에 성공했습니다"),
    SORT_STORE_PRODUCTS_SUCCESS("매장 상품 정렬에 성공했습니다");

    private final String message;
}
