package com.mohan.practice.errors;

public class InvalidRequestError extends AssertionError {

    public InvalidRequestError(String message){
        super(message);
    }
}
