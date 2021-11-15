package com.lemonado.smartmeet.core.data.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Can not find a user.");
    }
}
