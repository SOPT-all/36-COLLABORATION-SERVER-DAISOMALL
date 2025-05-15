package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.PopularProductListResponse;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public PopularProductListResponse getPopularProducts() {
        List<Product> products = productRepository.findTop20ByOrderById();
        return PopularProductListResponse.of(products, 5);
    }

}
