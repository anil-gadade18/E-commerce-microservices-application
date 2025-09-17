package com.embarkx.companyms.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalCompanyNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CompanyNotFoundErrorResponse> exceptionHandler(CompanyNotFoundException exception){
        CompanyNotFoundErrorResponse error = new CompanyNotFoundErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<CompanyNotFoundErrorResponse> exceptionHandler(Exception exception){
        CompanyNotFoundErrorResponse error = new CompanyNotFoundErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
