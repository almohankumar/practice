package com.mohan.practice.errors;

public class NotFoundError extends AssertionError {

    public NotFoundError(String message){
        super(message);
    }
}
