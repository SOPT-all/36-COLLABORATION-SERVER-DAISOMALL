package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

public record PopularProductListResponse(
        List<List<PopularProductResponse>> pages,
        int total
) {
    public static PopularProductListResponse of(List<Product> products, int chunkSize) {
        List<PopularProductResponse> all = products.stream()
                .map(PopularProductResponse::from)
                .toList();

        List<List<PopularProductResponse>> pages = new ArrayList<>();
        for (int i = 0; i < all.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, all.size());
            pages.add(all.subList(i, end));
        }

        return new PopularProductListResponse(pages, all.size());
    }
}

