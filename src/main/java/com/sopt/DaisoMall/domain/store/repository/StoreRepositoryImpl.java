package com.sopt.DaisoMall.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.store.dto.response.StoreSearchResponse;
import com.sopt.DaisoMall.domain.store.entity.QStore;
import com.sopt.DaisoMall.domain.store.entity.QStoreProductStock;
import com.sopt.DaisoMall.domain.store.entity.enums.StoreType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private static final QStoreProductStock SPS = QStoreProductStock.storeProductStock;
    private static final QStore STORE = QStore.store;
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<StoreSearchResponse> searchStoreWithPriorityByName(
            Long productId,
            String keyword,
            Pageable pageable
    ) {

        BooleanBuilder builder = new BooleanBuilder(SPS.product.id.eq(productId));
        if (StringUtils.hasText(keyword)) {
            builder.and(STORE.storeName.containsIgnoreCase(keyword));
        }


        List<StoreSearchResponse> content = queryFactory
                .select(Projections.constructor(
                        StoreSearchResponse.class,
                        STORE.id,
                        STORE.storeName,
                        STORE.location,
                        STORE.openingHours,
                        STORE.storeType.stringValue(),
                        SPS.stockCount,
                        SPS.shelfNo,
                        SPS.floor
                ))
                .from(SPS)
                .join(SPS.store, STORE)
                .where(builder)
                .orderBy(
                        new CaseBuilder().when(SPS.stockCount.gt(0)).then(1).otherwise(0).desc(),
                        new CaseBuilder().when(STORE.storeType.eq(StoreType.FRANCHISE)).then(1).otherwise(0).desc(),
                        new CaseBuilder().when(STORE.storeType.eq(StoreType.DISTRIBUTION)).then(1).otherwise(0).desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}
