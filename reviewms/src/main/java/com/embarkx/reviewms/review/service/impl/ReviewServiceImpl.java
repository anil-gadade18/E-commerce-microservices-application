package com.embarkx.reviewms.review.service.impl;


import com.embarkx.reviewms.review.exception.ReviewNotFoundException;
import com.embarkx.reviewms.review.entity.Review;
import com.embarkx.reviewms.review.repository.ReviewRepository;
import com.embarkx.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired private ReviewRepository reviewRepository;


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return this.reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review createReview(Long companyId,Review review) {

        if (companyId != null && review != null){
            review.setCompanyId(companyId);
            return reviewRepository.save(review);
        }else{
            return null;
        }
    }

    @Override
    public Review getSingleReview(Long reviewId) {
       return reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundException("Review Not Found : "+reviewId));
    }


    @Override
    public void deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundException("Review Not Found : "+reviewId));
        if(review !=null )
               reviewRepository.delete(review);
            }

    @Override
    public Review updateReview(Long reviewId, Review updatedReview) {

        Review review  =  reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundException("Review Not Found : "+reviewId));
        if( review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return review;
        }
        return null;
    }
}


