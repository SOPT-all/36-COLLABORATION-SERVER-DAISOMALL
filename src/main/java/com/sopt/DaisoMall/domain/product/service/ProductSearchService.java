package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.brand.repository.BrandRepository;
import com.sopt.DaisoMall.domain.product.dto.response.ProductBrandResponse;
import com.sopt.DaisoMall.domain.product.dto.response.ProductResponse;
import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final ProductStockRepository stockRepository;
    private final BrandRepository brandRepository;

    public Slice<ProductResponse> searchProducts(
            String keyword,
            int pageNumber,
            int pageSize
    ) {
        if (pageNumber < 0)
            throw new PageNotFoundException();
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        if (brandRepository.existsByNameIgnoreCase(keyword)) {
            return productRepository
                    .findDistinctByBrand_NameContainingIgnoreCase(keyword, page)
                    .map(ProductResponse::from);
        }
        return stockRepository
                .searchByKeyword(keyword, page)
                .map(ProductResponse::from);
    }

    public Slice<ProductBrandResponse> getBrandProducts(Long brandId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByBrandId(brandId, pageable)
                .map(ProductBrandResponse::from);
    }
}
