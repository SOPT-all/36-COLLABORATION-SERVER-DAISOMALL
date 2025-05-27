package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.ProductCategoryResponse;
import com.sopt.DaisoMall.domain.product.entity.enums.Category;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import com.sopt.DaisoMall.domain.tag.repository.ProductTagMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductRepository productRepository;
    private final ProductTagMappingRepository productTagMappingRepository;

    public Slice<ProductCategoryResponse> getProductsByCategory(Category category, int pageNumber, int pageSize) {
        List<ProductCategoryResponse> products = productRepository.fetchPagedProducts(category, pageNumber, pageSize);
        boolean hasNext = products.size() > pageSize;
        if (hasNext) products.remove(pageSize);

        List<Long> productIds = products.stream().map(ProductCategoryResponse::productId).toList();
        Map<Long, List<String>> tagMap = productTagMappingRepository.fetchTagsGroupedByProduct(productIds);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<ProductCategoryResponse> enriched = products.stream()
                .map(dto -> dto.withTags(tagMap.getOrDefault(dto.productId(), List.of())))
                .toList();

        return new SliceImpl<>(enriched, pageable, hasNext);
    }
}
