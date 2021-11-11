package com.lemonado.smartmeet.core.data.models.timeline;

import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.LocalDateTime;

public record TimeLineModel(
        long id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        GroupModel groupModel,
        UserModel user,
        TimeLineType timeLineType) {

}
