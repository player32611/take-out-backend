package com.player32611.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(){}

    public LoginFailedException(String message) {
        super(message);
    }
}
