package com.sopt.DaisoMall.domain.product.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.product.dto.response.ProductCategoryResponse;
import com.sopt.DaisoMall.domain.product.dto.response.list.PopularProductListResponse;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.QProduct;
import com.sopt.DaisoMall.domain.product.entity.QProductImage;
import com.sopt.DaisoMall.domain.product.entity.enums.Category;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sopt.DaisoMall.domain.tag.entity.QProductTag;
import com.sopt.DaisoMall.domain.tag.entity.QProductTagMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PopularProductService {

    private final ProductRepository productRepository;
    private final JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
    public PopularProductListResponse getPopularProducts() {
        List<Product> products = productRepository.findTop15ByOrderById();
        return PopularProductListResponse.of(products, 5);
    }

    public Slice<ProductCategoryResponse> getProductByCategory(String category, int pageNumber, int pageSize) {
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductTagMapping tagMapping = QProductTagMapping.productTagMapping;
        QProductTag tag = QProductTag.productTag;

        List<Tuple> results = queryFactory
                .select(
                        product.id,
                        product.productName,
                        productImage.imageUrl,
                        product.price,
                        product.category,
                        tag.displayName
                )
                .from(product)
                .leftJoin(productImage).on(
                        productImage.product.eq(product)
                                .and(productImage.isMain.isTrue())
                                .and(productImage.sortOrder.eq(0))
                )
                .leftJoin(tagMapping).on(tagMapping.product.eq(product))
                .leftJoin(tag).on(tagMapping.productTag.eq(tag))
                .where(product.category.eq(Category.valueOf(category)))
                .orderBy(product.id.desc())
                .offset(pageNumber * pageSize)
                .limit(pageSize + 1)
                .fetch();

        Map<Long, ProductCategoryResponse> grouped = new LinkedHashMap<>();
        for (Tuple tuple : results) {
            Long id = tuple.get(product.id);
            grouped.computeIfAbsent(id, k -> new ProductCategoryResponse(
                    id,
                    tuple.get(product.productName),
                    tuple.get(productImage.imageUrl), // ✅ image
                    tuple.get(product.price),
                    tuple.get(product.category).name(),
                    new ArrayList<>()
            ));
            String tagName = tuple.get(tag.displayName);
            if (tagName != null) {
                grouped.get(id).tags().add(tagName);
            }
        }

        List<ProductCategoryResponse> content = new ArrayList<>(grouped.values());
        boolean hasNext = content.size() > pageSize;
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return new SliceImpl<>(content, PageRequest.of(pageNumber, pageSize), hasNext);
    }


}
