package com.embarkx.companyms.company.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String s) {
        super(s);
    }
}
