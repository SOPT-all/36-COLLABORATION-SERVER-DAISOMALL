package com.sopt.DaisoMall.domain.brand.controller;

import com.sopt.DaisoMall.domain.product.controller.ResponseMessage;
import com.sopt.DaisoMall.domain.product.dto.request.ProductSearchRequest;
import com.sopt.DaisoMall.domain.product.dto.response.ProductBrandResponse;
import com.sopt.DaisoMall.domain.product.dto.response.list.ProductBrandListResponse;
import com.sopt.DaisoMall.domain.product.service.ProductSearchService;
import com.sopt.DaisoMall.global.common.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final ProductSearchService searchService;

    @Operation(summary = "브랜드별 상품 조회")
    @GetMapping("/{brandId}")
    public ApiResponse<ProductBrandListResponse> getBrandProducts(@PathVariable Long brandId, ProductSearchRequest request) {
        Slice<ProductBrandResponse> slice = searchService.getBrandProducts(brandId, request.pageNumber(), request.pageSize());
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.GET_BRAND_PRODUCTS_SUCCESS.getMessage(), ProductBrandListResponse.of(slice));
    }
}
