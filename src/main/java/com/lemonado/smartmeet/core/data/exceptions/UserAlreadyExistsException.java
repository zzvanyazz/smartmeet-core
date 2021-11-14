package com.lemonado.smartmeet.core.data.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String normalisedUserId) {
        super(String.format("User with name '%s' already exists", normalisedUserId));
    }
}
