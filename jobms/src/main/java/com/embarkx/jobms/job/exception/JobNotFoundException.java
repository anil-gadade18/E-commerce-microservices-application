package com.embarkx.jobms.job.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String s) {
        super(s);
    }
}
