package com.sopt.DaisoMall.domain.product.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SEARCH_STORE_PRODUCTS_SUCCESS("상품 검색에 성공했습니다"),
    SORT_STORE_PRODUCTS_SUCCESS("상품 정렬에 성공했습니다"),
    GET_POPULAR_PRODUCTS_SUCCESS("지금 많이 찾는 상품 조회에 성공했습니다"),
    GET_BRAND_PRODUCTS_SUCCESS("브랜드별 상품 조회에 성공했습니다"),
    GET_PRODUCT_CATEGORY_SUCCESS("카테고리별 상품 조회에 성공했습니다."),
    GET_PRODUCT_DETAIL_SUCCESS("상품 상세 조회에 성공했습니다.");

    private final String message;
}
