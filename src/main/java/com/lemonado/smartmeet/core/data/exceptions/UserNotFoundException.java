package com.lemonado.smartmeet.core.data.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Can not find a user.");
    }
}
