package com.sopt.DaisoMall.domain.product.dto.response;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;

public record ProductResponse(
        Long productId,
        String productName,
        int shelfNo,
        int price
) {
    public static ProductResponse from(StoreProductStock stock) {
        Product product = stock.getProduct();
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                stock.getShelfNo(),
                product.getPrice()
        );
    }
}
