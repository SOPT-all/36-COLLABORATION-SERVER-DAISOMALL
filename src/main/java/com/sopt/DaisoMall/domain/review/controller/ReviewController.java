package com.sopt.DaisoMall.domain.review.controller;

import com.sopt.DaisoMall.domain.review.dto.request.ReviewRequest;
import com.sopt.DaisoMall.domain.review.dto.response.ReviewListResponse;
import com.sopt.DaisoMall.domain.review.dto.response.ReviewResponse;
import com.sopt.DaisoMall.domain.review.service.ReviewService;
import com.sopt.DaisoMall.global.common.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "상품에 대한 전체 리뷰를 조회합니다.")
    @GetMapping
    public ApiResponse<ReviewListResponse> getReviews(@RequestParam("productId") long productId, ReviewRequest request){
        Slice<ReviewResponse> slice = reviewService.getReviews(productId, request.pageSize(), request.pageNumber());
        return ApiResponse.response(HttpStatus.OK.value(),ResponseMessage.GET_ALL_REVIEW_SUCCESS.getMessage(), ReviewListResponse.of(slice));
    }
}
