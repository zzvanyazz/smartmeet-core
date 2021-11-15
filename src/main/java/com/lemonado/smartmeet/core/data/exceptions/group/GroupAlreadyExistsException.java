package com.lemonado.smartmeet.core.data.exceptions.group;

public class GroupAlreadyExistsException extends Exception {

    public GroupAlreadyExistsException() {
        super("Group already exists.");
    }

}
