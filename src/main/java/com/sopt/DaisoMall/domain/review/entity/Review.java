package com.sopt.DaisoMall.domain.review.entity;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Review extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private String nickname;

    private String profileImageUrl;

    @Column(nullable = false) @Min(1) @Max(5)
    private int rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
