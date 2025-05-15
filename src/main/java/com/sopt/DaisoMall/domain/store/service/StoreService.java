package com.sopt.DaisoMall.domain.store.service;

import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.store.dto.StoreStockResponse;
import com.sopt.DaisoMall.domain.store.repository.StoreStockRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StoreService {

    private final StoreStockRepository stockRepository;

    public StoreService(StoreStockRepository stockRepository){
        this.stockRepository = stockRepository;
    }
    public Slice<StoreStockResponse> getStoreStockList(Long productId, int pageNumber, int pageSize){
        if (pageNumber < 0)
            throw new PageNotFoundException();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return stockRepository.findByProductId(productId, pageable)
                .map(StoreStockResponse::from);
    }
}
