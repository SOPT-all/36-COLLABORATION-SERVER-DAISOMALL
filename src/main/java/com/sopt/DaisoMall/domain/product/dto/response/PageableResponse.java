package com.sopt.DaisoMall.domain.product.dto.response;


import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder
public record PageableResponse(
        int pageNumber,
        int pageSize,
        int numberOfElements,
        boolean isLast
) {
    public static PageableResponse of(Slice<?> slice) {
        return PageableResponse.builder()
                .pageNumber(slice.getNumber())
                .pageSize(slice.getSize())
                .numberOfElements(slice.getNumberOfElements())
                .isLast(slice.isLast())
                .build();
    }
}
