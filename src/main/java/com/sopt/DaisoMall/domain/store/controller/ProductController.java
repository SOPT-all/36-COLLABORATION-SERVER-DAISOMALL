package com.sopt.DaisoMall.domain.store.controller;


import com.sopt.DaisoMall.domain.store.dto.request.StoreProductSearchRequest;
import com.sopt.DaisoMall.domain.store.dto.request.StoreProductSortRequest;
import com.sopt.DaisoMall.domain.store.dto.response.StoreProductListResponse;
import com.sopt.DaisoMall.domain.store.dto.response.StoreProductResponse;
import com.sopt.DaisoMall.domain.store.entity.enums.SortOption;
import com.sopt.DaisoMall.domain.store.service.StoreProductSearchService;
import com.sopt.DaisoMall.domain.store.service.StoreProductSortService;
import com.sopt.DaisoMall.global.common.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final StoreProductSortService sortService;
    private final StoreProductSearchService searchService;

    @Operation(summary = "매장 상품 검색 (상품명, 품번, 브랜드)")
    @GetMapping("/search")
    public ApiResponse<StoreProductListResponse> search(@RequestParam String keyword, StoreProductSearchRequest request) {
        Slice<StoreProductResponse> slice = searchService.searchProducts(keyword, request.pageNumber(), request.pageSize());
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.SEARCH_STORE_PRODUCTS_SUCCESS.getMessage(), StoreProductListResponse.of(slice));
    }

    @Operation(summary = "검색된 상품 정렬 (최신,가격 낮은 순, 높은 순)")
    @GetMapping("/search/{keyword}/sort")
    public ApiResponse<StoreProductListResponse> sort(@PathVariable String keyword, StoreProductSortRequest request) {
        SortOption option = request.toSortOption();
        Slice<StoreProductResponse> slice = sortService.sortProducts(keyword, option, request.pageNumber(), request.pageSize());

        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.SORT_STORE_PRODUCTS_SUCCESS.getMessage(), StoreProductListResponse.of(slice));
    }
}
