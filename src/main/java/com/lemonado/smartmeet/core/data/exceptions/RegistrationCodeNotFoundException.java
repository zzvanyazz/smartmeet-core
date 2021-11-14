package com.lemonado.smartmeet.core.data.exceptions;

public class RegistrationCodeNotFoundException extends RuntimeException {
    public RegistrationCodeNotFoundException() {
        super("Invalid registration code");
    }
}
