package com.embarkx.reviewms.review.exception;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message){
        super(message);
    }
}
