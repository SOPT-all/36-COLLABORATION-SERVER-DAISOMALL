package com.sopt.DaisoMall.domain.product.controller;


import com.sopt.DaisoMall.domain.product.dto.request.ProductSearchRequest;
import com.sopt.DaisoMall.domain.product.dto.request.ProductSortRequest;
import com.sopt.DaisoMall.domain.product.dto.response.ProductDetailResponse;
import com.sopt.DaisoMall.domain.product.dto.response.ProductResponse;
import com.sopt.DaisoMall.domain.product.dto.response.list.PopularProductListResponse;
import com.sopt.DaisoMall.domain.product.dto.response.list.ProductListResponse;
import com.sopt.DaisoMall.domain.product.entity.enums.SortOption;
import com.sopt.DaisoMall.domain.product.service.PopularProductService;
import com.sopt.DaisoMall.domain.product.service.ProductDetailService;
import com.sopt.DaisoMall.domain.product.service.ProductSearchService;
import com.sopt.DaisoMall.domain.product.service.ProductSortService;
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
    private final ProductSortService sortService;
    private final ProductSearchService searchService;
    private final PopularProductService popularProductService;
    private final ProductDetailService productDetailService;

    @Operation(summary = "상품 검색 (상품명, 품번, 브랜드)", description = "브랜드는 VT, 비즈, 락앤락 중 하나로 가능합니다.")
    @GetMapping("/search")
    public ApiResponse<ProductListResponse> search(@RequestParam String keyword, ProductSearchRequest request) {
        Slice<ProductResponse> slice = searchService.searchProducts(keyword, request.pageNumber(), request.pageSize());
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.SEARCH_STORE_PRODUCTS_SUCCESS.getMessage(), ProductListResponse.of(slice));
    }

    @Operation(summary = "상품 정렬 (최신순, 가격 낮은 순, 가격 높은 순)", description = "SortOption은 최신순, 가격 낮은 순, 가격 높은 순으로 가능합니다.")
    @GetMapping("/search/{keyword}/sort")
    public ApiResponse<ProductListResponse> sort(@PathVariable String keyword, ProductSortRequest request) {
        SortOption option = request.toSortOption();
        Slice<ProductResponse> slice = sortService.sortProducts(keyword, option, request.pageNumber(), request.pageSize());

        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.SORT_STORE_PRODUCTS_SUCCESS.getMessage(), ProductListResponse.of(slice));
    }

    @Operation(summary = "지금 많이 찾는 상품 조회", description = "지금 많이 찾는 상품 15개를 5개씩 3페이지로 나누어 리스트로 반환합니다.")
    @GetMapping("/popular")
    public ApiResponse<PopularProductListResponse> search() {
        PopularProductListResponse response = popularProductService.getPopularProducts();
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.GET_POPULAR_PRODUCTS_SUCCESS.getMessage(), response);
    }

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> getProductDetail(@PathVariable("productId") long productId){
        ProductDetailResponse response = productDetailService.getProductDetail(productId);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.GET_PRODUCT_DETAIL_SUCCESS.getMessage(), response);
    }
}
