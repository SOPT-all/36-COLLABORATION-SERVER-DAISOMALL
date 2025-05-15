package com.sopt.DaisoMall.domain.store.controller;

import com.sopt.DaisoMall.domain.store.controller.ResponseMessage;
import com.sopt.DaisoMall.domain.store.dto.StoreFilterRequest;
import com.sopt.DaisoMall.domain.store.dto.StoreStockListResponse;
import com.sopt.DaisoMall.domain.store.dto.StoreStockRequest;
import com.sopt.DaisoMall.domain.store.dto.StoreStockResponse;
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
}
