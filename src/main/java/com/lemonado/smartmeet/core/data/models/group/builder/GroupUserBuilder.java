package com.lemonado.smartmeet.core.data.models.group.builder;

import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import com.lemonado.smartmeet.core.data.models.group.AddedUserStatus;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.LocalDateTime;

public class GroupUserBuilder {

    private GroupModel groupModel;
    private UserModel user;
    private AddedUserStatus status;
    private LocalDateTime inviteTime;

    public static GroupUserBuilder builder() {
        return new GroupUserBuilder();
    }

    public static GroupUserBuilder from(GroupUserModel addedUser) {
        GroupUserBuilder builder = new GroupUserBuilder();
        builder.groupModel = addedUser.groupModel();
        builder.user = addedUser.user();
        builder.status = addedUser.status();
        builder.inviteTime = addedUser.inviteTime();
        return builder;
    }

    public GroupUserBuilder withGroupModel(GroupModel groupModel) {
        this.groupModel = groupModel;
        return this;
    }

    public GroupUserBuilder withUser(UserModel user) {
        this.user = user;
        return this;
    }

    public GroupUserBuilder withStatus(AddedUserStatus status) {
        this.status = status;
        return this;
    }

    public GroupUserBuilder withInviteTime(LocalDateTime inviteTime) {
        this.inviteTime = inviteTime;
        return this;
    }

    public GroupUserModel build() {
        return new GroupUserModel(groupModel, user, status, inviteTime);
    }
}
