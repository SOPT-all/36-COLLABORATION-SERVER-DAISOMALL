package com.sopt.DaisoMall.domain.product.service;

import com.sopt.DaisoMall.domain.product.dto.response.list.PopularProductListResponse;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PopularProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public PopularProductListResponse getPopularProducts() {
        List<Product> products = productRepository.findTop15ByOrderById();
        return PopularProductListResponse.of(products, 5);
    }

}
