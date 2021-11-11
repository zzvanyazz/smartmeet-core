package com.lemonado.smartmeet.core.data.exceptions;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
