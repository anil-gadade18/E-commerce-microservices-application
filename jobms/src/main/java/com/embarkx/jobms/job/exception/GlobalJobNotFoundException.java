package com.embarkx.jobms.job.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalJobNotFoundException {

    @ExceptionHandler
    public ResponseEntity<JobNotFoundErrorResponse> exceptionHandler(JobNotFoundException exception){
        JobNotFoundErrorResponse error = new JobNotFoundErrorResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(new Date());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<JobNotFoundErrorResponse> exceptionHandler(Exception exception){
        JobNotFoundErrorResponse error = new JobNotFoundErrorResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(new Date());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
