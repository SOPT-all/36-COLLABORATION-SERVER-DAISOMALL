package com.sopt.DaisoMall.domain.review.service;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.exception.NotFoundProductException;
import com.sopt.DaisoMall.domain.product.exception.PageNotFoundException;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import com.sopt.DaisoMall.domain.review.dto.response.ReviewImageResponse;
import com.sopt.DaisoMall.domain.review.dto.response.ReviewResponse;
import com.sopt.DaisoMall.domain.review.entity.Review;
import com.sopt.DaisoMall.domain.review.repository.ReviewImageRepository;
import com.sopt.DaisoMall.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    public Slice<ReviewResponse> getReviews(long productId, int pageSize, int pageNumber){
        if (pageNumber < 0) throw new PageNotFoundException();

        Product product = productRepository.findById(productId).orElseThrow(NotFoundProductException::new);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Slice<Review> reviews = reviewRepository.findByProduct(product, pageable);
        return reviews.map(review ->
                ReviewResponse.from(
                        review,
                        review.getImages().stream()
                                .map(ReviewImageResponse::from)
                                .toList()
                )
        );


    }
}
