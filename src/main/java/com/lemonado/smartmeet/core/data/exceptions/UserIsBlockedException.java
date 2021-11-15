package com.lemonado.smartmeet.core.data.exceptions;

public class UserIsBlockedException extends Exception {

    public UserIsBlockedException() {
        super("This user is blocked.");
    }
}
