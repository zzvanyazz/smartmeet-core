package com.lemonado.smartmeet.core.data.exceptions;

public class UserIsBlockedException extends RuntimeException {

    public UserIsBlockedException() {
        super("This user is blocked.");
    }
}
