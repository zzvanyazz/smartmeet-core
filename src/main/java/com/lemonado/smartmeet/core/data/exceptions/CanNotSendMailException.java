package com.lemonado.smartmeet.core.data.exceptions;

public class CanNotSendMailException extends Exception {

    public CanNotSendMailException(String mail) {
        super(String.format("Can not send letter to '%s'", mail));
    }
}
