package com.cloudHumans.BackendTest.exceptions;

public class UnderAgeException extends RuntimeException {

    public UnderAgeException(String msg){
        super(msg);
    }
}
