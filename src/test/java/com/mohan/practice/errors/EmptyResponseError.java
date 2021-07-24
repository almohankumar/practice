package com.mohan.practice.errors;

public class EmptyResponseError extends AssertionError {

    public EmptyResponseError(String message){
        super(message);
    }
}
