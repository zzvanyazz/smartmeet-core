package com.lemonado.smartmeet.core.data.models.group;

import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.util.Set;

public record GroupModel(
        long id,
        String name,
        UserModel creator,
        String code,
        Set<GroupUserModel> users) {
}
