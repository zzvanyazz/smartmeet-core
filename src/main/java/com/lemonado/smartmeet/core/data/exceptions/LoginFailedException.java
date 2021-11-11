package com.lemonado.smartmeet.core.data.exceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException() {
        this("Username or password is incorrect.");
    }
    public LoginFailedException(String message) {
        super(message);
    }
}
