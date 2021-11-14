package com.lemonado.smartmeet.core.data.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
