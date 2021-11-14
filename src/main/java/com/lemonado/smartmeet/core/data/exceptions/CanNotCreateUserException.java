package com.lemonado.smartmeet.core.data.exceptions;

public class CanNotCreateUserException extends RuntimeException {
    public CanNotCreateUserException() {
        super("Can not create user");
    }
}
