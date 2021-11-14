package com.lemonado.smartmeet.core.data.models.group;

import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.LocalDateTime;

public record GroupUserModel(
        GroupModel groupModel,
        UserModel user,
        AddedUserStatus status,
        LocalDateTime inviteTime) {

}
