package com.lemonado.smartmeet.core.data.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        this("Username or password is incorrect.");
    }
    public LoginFailedException(String message) {
        super(message);
    }
}
