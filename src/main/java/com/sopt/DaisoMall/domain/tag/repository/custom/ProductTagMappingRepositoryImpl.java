package com.sopt.DaisoMall.domain.tag.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.tag.dto.ProductTagDto;
import com.sopt.DaisoMall.domain.tag.entity.QProductTag;
import com.sopt.DaisoMall.domain.tag.entity.QProductTagMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductTagMappingRepositoryImpl implements ProductTagMappingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Map<Long, List<String>> fetchTagsGroupedByProduct(List<Long> productIds) {
        QProductTagMapping tagMapping = QProductTagMapping.productTagMapping;
        QProductTag tag = QProductTag.productTag;

        List<ProductTagDto> tags = queryFactory
                .select(Projections.constructor(ProductTagDto.class,
                        tagMapping.product.id,
                        tag.displayName
                ))
                .from(tagMapping)
                .join(tag).on(tagMapping.productTag.eq(tag))
                .where(tagMapping.product.id.in(productIds))
                .fetch();

        return tags.stream()
                .collect(Collectors.groupingBy(
                        ProductTagDto::productId,
                        Collectors.mapping(ProductTagDto::tag, Collectors.toList())
                ));
    }
}
