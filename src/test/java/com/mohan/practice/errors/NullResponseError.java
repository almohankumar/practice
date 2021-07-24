package com.mohan.practice.errors;

public class NullResponseError extends AssertionError {

    public NullResponseError(String message){
        super(message);
    }
}
