package com.sopt.DaisoMall.domain.product.service;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.DaisoMall.domain.brand.entity.QBrand;
import com.sopt.DaisoMall.domain.image.dto.request.ProductImageDto;
import com.sopt.DaisoMall.domain.product.dto.querydsl.QueryResultDto;
import com.sopt.DaisoMall.domain.product.dto.response.ProductDetailResponse;
import com.sopt.DaisoMall.domain.product.dto.response.ProductImageResponse;
import com.sopt.DaisoMall.domain.product.entity.QProduct;
import com.sopt.DaisoMall.domain.product.entity.QProductImage;
import com.sopt.DaisoMall.domain.review.entity.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailService {

    private final JPAQueryFactory queryFactory;

    public ProductDetailResponse getProductDetail(long productId) {
        List<ProductImageDto> mainImages = fetchImages(productId, true);
        List<ProductImageDto> detailImages = fetchImages(productId, false);

        QueryResultDto result = fetchProductDetail(productId, mainImages, detailImages);

//        if (result == null) {
//            throw new ProductNotFoundException();
//        }

        return ProductDetailResponse.from(result, mainImages, detailImages);
    }

    private List<ProductImageDto> fetchImages(Long productId, boolean isMain) {
        QProductImage image = QProductImage.productImage;
        return queryFactory
                .select(Projections.constructor(ProductImageDto.class,
                        image.id,
                        image.imageUrl,
                        image.sortOrder))
                .from(image)
                .where(image.product.id.eq(productId)
                        .and(image.isMain.eq(isMain)))
                .orderBy(image.sortOrder.asc())
                .fetch();
    }


    private QueryResultDto fetchProductDetail(Long productId, List<ProductImageDto> mainImages, List<ProductImageDto> detailImages) {
        QProduct product = QProduct.product;
        QBrand brand = QBrand.brand;
        QReview review = QReview.review;

        return queryFactory
                .select(Projections.constructor(QueryResultDto.class,
                        product.id,
                        product.productName,
                        product.price.stringValue(),
                        review.rating.avg().coalesce(0.0),
                        review.countDistinct(),
                        brand.name
                ))
                .from(product)
                .join(product.brand, brand)
                .leftJoin(review).on(review.product.id.eq(product.id))
                .where(product.id.eq(productId))
                .groupBy(product.id, brand.id)
                .fetchOne();
    }

}
