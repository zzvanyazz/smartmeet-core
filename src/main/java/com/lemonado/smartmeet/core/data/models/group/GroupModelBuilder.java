package com.lemonado.smartmeet.core.data.models.group;

import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.util.HashSet;
import java.util.Set;

public class GroupModelBuilder {

    private long id = 0;
    private String name;
    private UserModel creator;
    private String code;
    private Set<UserModel> users = new HashSet<>();

    public static GroupModelBuilder builder() {
        return new GroupModelBuilder();
    }

    public GroupModelBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public GroupModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupModelBuilder withCreator(UserModel creator) {
        this.creator = creator;
        return this;
    }

    public GroupModelBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public GroupModelBuilder withUsers(Set<UserModel> users) {
        this.users = users;
        return this;
    }

    public GroupModel build() {
        return new GroupModel(id, name, creator, code, users);
    }
}
