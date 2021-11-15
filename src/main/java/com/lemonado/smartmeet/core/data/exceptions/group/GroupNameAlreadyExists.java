package com.lemonado.smartmeet.core.data.exceptions.group;

public class GroupNameAlreadyExists extends Exception {
    public GroupNameAlreadyExists() {
        super("Group which such name already exists.");
    }
}
