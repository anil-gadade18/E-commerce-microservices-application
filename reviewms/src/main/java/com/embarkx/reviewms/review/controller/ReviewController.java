package com.embarkx.reviewms.review.controller;



import com.embarkx.reviewms.review.exception.ReviewNotFoundException;
import com.embarkx.reviewms.review.entity.Review;
import com.embarkx.reviewms.review.messaging.ReviewMessageProducer;
import com.embarkx.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

        @Autowired private ReviewService reviewService;
        @Autowired private ReviewMessageProducer reviewMessageProducer;

        @GetMapping
        public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
            List<Review> reviews = this.reviewService.getAllReviews(companyId);
            if(reviews.isEmpty()){
                throw new ReviewNotFoundException("Review is not created for entered company Id : "+companyId);
            }else{
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            }
        }

        @PostMapping
        public ResponseEntity<Review> createReview(@RequestParam Long companyId,
                                                   @RequestBody Review review){

            Review addedReview = this.reviewService.createReview(companyId, review);
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>(addedReview,HttpStatus.CREATED);
        }

        @GetMapping("/{reviewId}")
        public ResponseEntity<Review> getSingleReview(@PathVariable Long reviewId){
            Review singleReview = this.reviewService.getSingleReview(reviewId);
            if(singleReview != null){
                return new ResponseEntity<>(singleReview,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }

        @PutMapping("/{reviewId}")
        public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                                   @RequestBody Review review){
            return new ResponseEntity<>(this.reviewService.updateReview(reviewId,review),HttpStatus.OK);
        }

        @DeleteMapping("/{reviewId}")
        public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
            this.reviewService.deleteReview(reviewId);
            return new ResponseEntity<>("Review has been deleted successfully : "+reviewId,HttpStatus.OK);
        }


       @GetMapping("/averageRating")
        public Double getAverageRating(@RequestParam Long companyId){
           List<Review> reviewList = reviewService.getAllReviews(companyId);
           return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
       }
}
