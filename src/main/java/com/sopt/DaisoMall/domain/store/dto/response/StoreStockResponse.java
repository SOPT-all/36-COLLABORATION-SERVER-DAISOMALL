package com.sopt.DaisoMall.domain.store.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.sopt.DaisoMall.domain.store.entity.Store;
import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;
import lombok.Getter;

@Getter
public class StoreStockResponse {
    private final String storeName;
    private final String location;
    private final String openingHours;
    private final Double latitude;
    private final Double longitude;
    private final Boolean isPickupAvailable;
    private final String floor;
    private final int shelfNo;
    private final long stockCount;
    private final String stockStatus;

    @QueryProjection
    public StoreStockResponse(String storeName, String location, String openingHours,
                              Double latitude, Double longitude, Boolean isPickupAvailable,
                              String floor, int shelfNo, long stockCount, String stockStatus) {
        this.storeName = storeName;
        this.location = location;
        this.openingHours = openingHours;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isPickupAvailable = isPickupAvailable;
        this.floor = floor;
        this.shelfNo = shelfNo;
        this.stockCount = stockCount;
        this.stockStatus = stockStatus;
    }

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
