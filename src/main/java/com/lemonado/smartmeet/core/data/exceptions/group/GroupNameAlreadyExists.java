package com.lemonado.smartmeet.core.data.exceptions.group;

public class GroupNameAlreadyExists extends Exception {
    public GroupNameAlreadyExists() {
        super("Group with such name already exists.");
    }
}
