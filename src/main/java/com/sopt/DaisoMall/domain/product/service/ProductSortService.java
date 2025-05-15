package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.ProductResponse;
import com.sopt.DaisoMall.domain.product.entity.enums.SortOption;
import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.product.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductSortService {
    private final ProductStockRepository stockRepository;

    public Slice<ProductResponse> sortProducts(
            String keyword,
            SortOption option,
            int pageNumber,
            int pageSize
    ) {
        if (pageNumber < 0)
            throw new PageNotFoundException();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, option.toSort());
        return stockRepository.searchByKeyword(keyword, pageable)
                .map(ProductResponse::from);
    }
}
