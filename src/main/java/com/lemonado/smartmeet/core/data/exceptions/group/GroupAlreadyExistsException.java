package com.lemonado.smartmeet.core.data.exceptions.group;

public class GroupAlreadyExistsException extends RuntimeException {

    public GroupAlreadyExistsException() {
        super("Group already exists.");
    }

}
