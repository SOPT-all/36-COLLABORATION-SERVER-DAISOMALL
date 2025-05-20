package com.sopt.DaisoMall.domain.image.service;

import com.sopt.DaisoMall.domain.image.dto.request.ReviewImageUploadDto;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.exception.NotFoundProductException;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import com.sopt.DaisoMall.domain.review.entity.Review;
import com.sopt.DaisoMall.domain.review.entity.ReviewImage;
import com.sopt.DaisoMall.domain.review.exception.NotFoundReviewException;
import com.sopt.DaisoMall.domain.review.repository.ReviewImageRepository;
import com.sopt.DaisoMall.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewImageService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void saveReviewImage(ReviewImageUploadDto dto, String imageUrl){
        Review review = reviewRepository.findById(dto.reviewId()).orElseThrow(NotFoundReviewException::new);

        int nextSortOrder = reviewImageRepository.countByReview(review);

        ReviewImage reviewImage = ReviewImage.builder()
                .review(review)
                .imageUrl(imageUrl)
                .isMain(dto.isMain())
                .sortOrder(nextSortOrder)
                .build();

        reviewImageRepository.save(reviewImage);
    }

    @Transactional
    public void deleteReviewImages(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        List<Review> reviews = reviewRepository.findByProduct(product);

        if (reviews.isEmpty()) return;

        reviewImageRepository.deleteByReviewIn(reviews);
    }

    public String getReviewImageKey(MultipartFile file, long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(NotFoundReviewException::new);
        String fileName = generateFileName(file);

        return String.format("/%d/reviews/%d/%s", review.getProduct().getId(), reviewId, fileName);
    }

    // 파일 이름 -> UUID로 설정
    private String generateFileName(MultipartFile file) {
        String extension = "";
        String original = file.getOriginalFilename();
        if (original != null && original.contains(".")) {
            extension = original.substring(original.lastIndexOf("."));
        }
        return UUID.randomUUID() + extension;
    }
}
