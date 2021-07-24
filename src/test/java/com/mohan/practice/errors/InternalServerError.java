package com.mohan.practice.errors;

public class InternalServerError extends AssertionError {

    public InternalServerError(String message){
        super(message);
    }
}
