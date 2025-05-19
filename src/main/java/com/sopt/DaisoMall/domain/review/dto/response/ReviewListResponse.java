package com.sopt.DaisoMall.domain.review.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
public record ReviewListResponse(
        List<ReviewResponse> reviews,
        PageableResponse pageable
) {
    public static ReviewListResponse of(Slice<ReviewResponse> slice){
        return ReviewListResponse.builder()
                .reviews(slice.getContent())
                .pageable(PageableResponse.of(slice))
                .build();
    }
}
