package com.sopt.DaisoMall.domain.store.entity;

import com.sopt.DaisoMall.domain.store.entity.enums.StoreType;
import com.sopt.DaisoMall.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Store extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean isPickupAvailable = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreType storeType;

    @Column(nullable = false)
    private String openingHours;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
