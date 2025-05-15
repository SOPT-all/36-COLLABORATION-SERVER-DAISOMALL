package com.sopt.DaisoMall.domain.store.dto;

import com.sopt.DaisoMall.domain.store.entity.Store;
import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;

public record StoreStockResponse(
    String storeName,
    String location,
    String openingHours,
    Double latitude, // 위도
    Double longitude, // 경도
    Boolean isPickupAvailable,
    String floor,
    int shelfNo,
    long stockCount,
    String stockStatus
) {
    public static StoreStockResponse from(StoreProductStock stock) {
        Store store = stock.getStore();
        return new StoreStockResponse(
                store.getStoreName(),
                store.getLocation(),
                store.getOpeningHours(),
                store.getLatitude(),
                store.getLongitude(),
                store.isPickupAvailable(),
                stock.getFloor(),
                stock.getShelfNo(),
                stock.getStockCount(),
                stock.getStockStatus().getDisplayName()
        );
    }
}
