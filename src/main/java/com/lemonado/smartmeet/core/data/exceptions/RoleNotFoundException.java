package com.lemonado.smartmeet.core.data.exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String roleName) {
        super(String.format("Can not find role '%s'", roleName));
    }
    public RoleNotFoundException() {
        super("Can not find role");
    }
}
