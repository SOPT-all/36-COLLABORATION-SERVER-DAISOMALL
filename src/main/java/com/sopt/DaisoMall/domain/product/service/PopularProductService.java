package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.PopularProductResponse;
import com.sopt.DaisoMall.domain.product.dto.response.list.PopularProductListResponse;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PopularProductService {
    private static final int CHUNK_SIZE = 5;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public PopularProductListResponse getPopularProducts() {

        Page<PopularProductResponse> page = productRepository.findPopularProducts(
                PageRequest.of(0, 20, Sort.by("id"))
        );

        List<PopularProductResponse> content = page.getContent();
        List<List<PopularProductResponse>> pages = new ArrayList<>();

        for (int i = 0; i < content.size(); i += CHUNK_SIZE) {
            pages.add(content.subList(i, Math.min(i + CHUNK_SIZE, content.size())));
        }

        return new PopularProductListResponse(pages, content.size());
    }

}
