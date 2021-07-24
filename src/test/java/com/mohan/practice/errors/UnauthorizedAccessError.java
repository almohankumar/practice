package com.mohan.practice.errors;

public class UnauthorizedAccessError extends AssertionError {

    public UnauthorizedAccessError(String message){
        super(message);
    }
}
