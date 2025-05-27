package com.sopt.DaisoMall.domain.product.repository.custom;

import com.sopt.DaisoMall.domain.product.dto.response.ProductCategoryResponse;
import com.sopt.DaisoMall.domain.product.entity.enums.Category;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductCategoryResponse> fetchPagedProducts(Category category, int pageNumber, int pageSize);
}
