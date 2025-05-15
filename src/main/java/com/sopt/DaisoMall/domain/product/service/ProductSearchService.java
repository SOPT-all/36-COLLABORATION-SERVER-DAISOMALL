package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.ProductResponse;
import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.product.repository.ProductStockRepository;
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
public class ProductSearchService {
    private final ProductStockRepository stockRepository;

    public Slice<ProductResponse> searchProducts(
            String keyword,
            int pageNumber,
            int pageSize
    ) {
        if (pageNumber < 0)
            throw new PageNotFoundException();
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        return stockRepository
                .searchByKeyword(keyword, page)
                .map(ProductResponse::from);
    }
}
