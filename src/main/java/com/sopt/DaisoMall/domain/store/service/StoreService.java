package com.sopt.DaisoMall.domain.store.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.product.entity.enums.StockStatus;
import com.sopt.DaisoMall.domain.product.entity.enums.StoreType;
import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.store.dto.QStoreStockResponse;
import com.sopt.DaisoMall.domain.store.dto.StoreFilterRequest;
import com.sopt.DaisoMall.domain.store.dto.StoreStockResponse;
import com.sopt.DaisoMall.domain.store.entity.QStore;
import com.sopt.DaisoMall.domain.store.entity.QStoreProductStock;
import com.sopt.DaisoMall.domain.store.repository.StoreStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreStockRepository stockRepository;
    private final JPAQueryFactory queryFactory;

    public Slice<StoreStockResponse> getStoreStockList(Long productId, int pageNumber, int pageSize){
        if (pageNumber < 0)
            throw new PageNotFoundException();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return stockRepository.findByProductId(productId, pageable)
                .map(StoreStockResponse::from);
    }

    public Slice<StoreStockResponse> filterStoresWithPaging(Long productId, StoreFilterRequest request, Pageable pageable) {
        BooleanBuilder builder = buildFilterCondition(productId, request);
        List<StoreStockResponse> content = fetchFilteredStoreStock(builder, pageable);

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanBuilder buildFilterCondition(Long productId, StoreFilterRequest request) {
        QStore store = QStore.store;
        QStoreProductStock stock = QStoreProductStock.storeProductStock;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(stock.product.id.eq(productId));

        if (Boolean.TRUE.equals(request.pickup())) {
            builder.and(store.isPickupAvailable.isTrue());
        }

        if (Boolean.TRUE.equals(request.excludeOutOfStock())) {
            builder.and(stock.stockStatus.ne(StockStatus.OUT_OF_STOCK));
        }

        if (Boolean.TRUE.equals(request.excludeStoreType())) {
            builder.and(store.storeType.notIn(StoreType.DISTRIBUTION, StoreType.FRANCHISE));
        }

        return builder;
    }

    private List<StoreStockResponse> fetchFilteredStoreStock(BooleanBuilder builder, Pageable pageable) {
        QStore store = QStore.store;
        QStoreProductStock stock = QStoreProductStock.storeProductStock;

        return queryFactory
                .select(new QStoreStockResponse(
                        store.storeName,
                        store.location,
                        store.openingHours,
                        store.latitude,
                        store.longitude,
                        store.isPickupAvailable,
                        stock.floor,
                        stock.shelfNo,
                        stock.stockCount,
                        stock.stockStatus.stringValue()
                ))
                .from(stock)
                .join(stock.store, store)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

}

