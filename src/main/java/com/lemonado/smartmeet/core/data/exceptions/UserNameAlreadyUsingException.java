package com.lemonado.smartmeet.core.data.exceptions;

public class UserNameAlreadyUsingException extends Exception{
    public UserNameAlreadyUsingException() {
        super("User name already using.");
    }
}
