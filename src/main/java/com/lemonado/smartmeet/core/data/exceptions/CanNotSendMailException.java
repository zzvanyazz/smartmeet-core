package com.lemonado.smartmeet.core.data.exceptions;

public class CanNotSendMailException extends RuntimeException {

    public CanNotSendMailException(String mail) {
        super(String.format("Can not send letter to '%s'", mail));
    }
}
