package com.sopt.DaisoMall.domain.review.repository;

import com.sopt.DaisoMall.domain.review.entity.Review;
import com.sopt.DaisoMall.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    int countByReview(Review review);
    void deleteByReviewIn(List<Review> reviews);
}
