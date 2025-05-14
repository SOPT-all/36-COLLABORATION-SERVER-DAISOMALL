package com.sopt.DaisoMall.domain.store.service;

import com.sopt.DaisoMall.domain.store.dto.response.StoreProductResponse;
import com.sopt.DaisoMall.domain.store.entity.enums.SortOption;
import com.sopt.DaisoMall.domain.store.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.store.repository.StoreProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreProductSortService {
    private final StoreProductStockRepository stockRepository;

    public Slice<StoreProductResponse> sortProducts(
            String keyword,
            SortOption option,
            int pageNumber,
            int pageSize
    ) {
        if (pageNumber < 0)
            throw new PageNotFoundException();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, option.toSort());
        return stockRepository.searchByKeyword(keyword, pageable)
                .map(StoreProductResponse::from);
    }
}
