package com.sopt.DaisoMall.domain.store.service;

import com.sopt.DaisoMall.domain.store.dto.response.StoreProductResponse;
import com.sopt.DaisoMall.domain.store.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.store.repository.StoreProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreProductSearchService {
    private final StoreProductStockRepository stockRepository;

    public Slice<StoreProductResponse> searchProducts(
            String keyword,
            int pageNumber,
            int pageSize
    ) {
        if (pageNumber < 0)
            throw new PageNotFoundException();
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        return stockRepository
                .searchByKeyword(keyword, page)
                .map(StoreProductResponse::from);
    }
}
