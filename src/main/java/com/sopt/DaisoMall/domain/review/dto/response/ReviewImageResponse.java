package com.sopt.DaisoMall.domain.review.dto.response;

import com.sopt.DaisoMall.domain.review.entity.ReviewImage;
import lombok.Builder;

@Builder
public record ReviewImageResponse(
        long reviewImageId,
        String imageUrl,
        Boolean isMain,
        int sortOrder
) {
    public static ReviewImageResponse from(ReviewImage reviewImage){
        return ReviewImageResponse.builder()
                .reviewImageId(reviewImage.getId())
                .imageUrl(reviewImage.getImageUrl())
                .isMain(reviewImage.isMain())
                .sortOrder(reviewImage.getSortOrder())
                .build();
    }
}
