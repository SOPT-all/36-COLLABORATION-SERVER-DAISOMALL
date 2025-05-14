package com.sopt.DaisoMall.domain.tag.entity;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "label"}))
public class ProductTagMapping extends BaseEntity {

    @Id
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private ProductTag productTag;
}
