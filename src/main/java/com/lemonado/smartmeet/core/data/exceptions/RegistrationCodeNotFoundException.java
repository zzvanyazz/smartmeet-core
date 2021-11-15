package com.lemonado.smartmeet.core.data.exceptions;

public class RegistrationCodeNotFoundException extends Exception {
    public RegistrationCodeNotFoundException() {
        super("Invalid registration code");
    }
}
