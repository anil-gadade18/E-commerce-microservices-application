package com.embarkx.reviewms.review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalReviewNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ReviewNotFoundErrorResponse> exceptionHandler(ReviewNotFoundException exception){
        ReviewNotFoundErrorResponse error = new ReviewNotFoundErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ReviewNotFoundErrorResponse> exceptionHandler(Exception exception){
        ReviewNotFoundErrorResponse error = new ReviewNotFoundErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
