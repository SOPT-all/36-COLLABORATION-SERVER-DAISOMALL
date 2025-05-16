package com.sopt.DaisoMall.domain.store.controller;

import com.sopt.DaisoMall.domain.store.dto.request.StoreFilterRequest;
import com.sopt.DaisoMall.domain.store.dto.request.StoreSearchRequest;
import com.sopt.DaisoMall.domain.store.dto.request.StoreStockRequest;
import com.sopt.DaisoMall.domain.store.dto.response.StoreSearchListResponse;
import com.sopt.DaisoMall.domain.store.dto.response.StoreSearchResponse;
import com.sopt.DaisoMall.domain.store.dto.response.StoreStockListResponse;
import com.sopt.DaisoMall.domain.store.dto.response.StoreStockResponse;
import com.sopt.DaisoMall.domain.store.service.StoreService;
import com.sopt.DaisoMall.global.common.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @Operation(summary = "상품별 매장 재고 기본 조희")
    @GetMapping
    public ApiResponse<StoreStockListResponse> getStoreStockByProduct(@RequestParam Long productId, StoreStockRequest request) {
        Slice<StoreStockResponse> slice = storeService.getStoreStockList(productId, request.pageNumber(), request.pageSize());

        return ApiResponse.response(
                HttpStatus.OK.value(),
                ResponseMessage.GET_STORE_STOCKS_SUCCESS.getMessage(),
                StoreStockListResponse.of(slice));
    }

    @Operation(summary = "상품별 매장 필터 조회")
    @GetMapping("/filter")
    public ApiResponse<StoreStockListResponse> filterStoreStocks(@RequestParam Long productId, StoreStockRequest basicRequest, StoreFilterRequest filterRequest) {
        Pageable pageable = PageRequest.of(basicRequest.pageNumber(), basicRequest.pageSize());
        Slice<StoreStockResponse> slice = storeService.filterStoresWithPaging(productId, filterRequest, pageable);

        return ApiResponse.response(
                HttpStatus.OK.value(),
                ResponseMessage.STORE_STOCKS_FILTERING_SUCCESS.getMessage(),
                StoreStockListResponse.of(slice)
        );
    }

    @Operation(
            summary = "매장명으로 검색",
            description = """
            특정 상품(productId)에 입고된 매장 목록을 매장 이름(keyword)으로 검색
            -> productId: 조회할 상품의 고유 ID (필수)
            + keyword: 매장명에 포함된 키워드로 필터링 (선택)
            + pageNumber: 0부터 시작하는 페이지 번호 (필수)
            + pageSize: 한 페이지에 반환할 항목 수 (필수)
        """
    )
    @GetMapping("/search")
    public ApiResponse<StoreSearchListResponse> searchStoresByName(@RequestParam Long productId, @RequestParam(required = false) String keyword, StoreSearchRequest request) {
        Slice<StoreSearchResponse> slice = storeService.searchStoresByName(productId, keyword, request.pageNumber(), request.pageSize());

        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.SEARCH_STORES_SUCCESS.getMessage(), StoreSearchListResponse.of(slice));
    }
}
