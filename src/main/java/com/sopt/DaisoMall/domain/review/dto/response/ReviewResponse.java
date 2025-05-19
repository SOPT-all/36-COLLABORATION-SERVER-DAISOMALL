package com.sopt.DaisoMall.domain.review.dto.response;

import com.sopt.DaisoMall.domain.review.entity.Review;
import com.sopt.DaisoMall.domain.review.entity.ReviewImage;
import lombok.Builder;

import java.util.List;

@Builder
public record ReviewResponse(
        long reviewId,
        String nickname,
        String profileImageUrl,
        int rating,
        String content,
        List<ReviewImageResponse> images
) {
    public static ReviewResponse from(Review review, List<ReviewImageResponse> images){
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .nickname(review.getNickname())
                .profileImageUrl(review.getProfileImageUrl())
                .rating(review.getRating())
                .content(review.getContent())
                .images(images)
                .build();
    }
}
