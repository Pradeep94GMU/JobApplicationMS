package com.pradeep.reviewms.Review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    List<Review> getAllReview(Long companyId);

    List<Review> getTotalReview();

    boolean createReview(Long companyId, Review review);

    boolean updateReview(Review review, Long reviewId);

    Review getReview(Long reviewId);

    boolean deleteReviewByCompanyIdByReviewId(Long reviewId);
}
