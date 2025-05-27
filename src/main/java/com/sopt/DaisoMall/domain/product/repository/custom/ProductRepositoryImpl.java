package com.sopt.DaisoMall.domain.product.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.product.dto.response.ProductCategoryResponse;
import com.sopt.DaisoMall.domain.product.entity.QProduct;
import com.sopt.DaisoMall.domain.product.entity.QProductImage;
import com.sopt.DaisoMall.domain.product.entity.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductCategoryResponse> fetchPagedProducts(Category category, int pageNumber, int pageSize) {
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        return queryFactory
                .select(Projections.constructor(ProductCategoryResponse.class,
                        product.id,
                        product.productName,
                        productImage.imageUrl,
                        product.price,
                        product.category.stringValue()
                ))
                .from(product)
                .leftJoin(product.productImages, productImage)
                .on(productImage.isMain.isTrue()
                        .and(productImage.sortOrder.eq(0)))
                .where(product.category.eq(category))
                .orderBy(product.id.desc())
                .offset((long) pageNumber * pageSize)
                .limit(pageSize + 1)
                .fetch();
    }
}
