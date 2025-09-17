package com.embarkx.reviewms.review.service;



import com.embarkx.reviewms.review.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    Review createReview(Long companyId,Review review);
    Review getSingleReview(Long reviewId);
    void deleteReview(Long reviewId);
    Review updateReview(Long reviewId, Review updatedReview);

}
