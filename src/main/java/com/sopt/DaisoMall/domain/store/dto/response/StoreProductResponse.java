package com.sopt.DaisoMall.domain.store.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;

public record StoreProductResponse(
        Long productId,
        String productName,
        int shelfNo,
        int price
) {
    public static StoreProductResponse from(StoreProductStock stock) {
        Product product = stock.getProduct();
        return new StoreProductResponse(
                product.getId(),
                product.getProductName(),
                stock.getShelfNo(),
                product.getPrice()
        );
    }
}
