package com.lemonado.smartmeet.core.data.exceptions;

public class ActionOnAdminRoleException extends RuntimeException {

    public ActionOnAdminRoleException() {
        super("Can not assign or remove assign 'Admin' role.");
    }
}
