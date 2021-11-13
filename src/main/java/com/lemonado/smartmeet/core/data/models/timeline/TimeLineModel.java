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

    public boolean intersects(TimeLineModel timeLineModel) {
        return includes(timeLineModel.startDate) ||
                includes(timeLineModel.endDate) ||
                timeLineModel.includes(startDate) ||
                timeLineModel.includes(endDate);
    }

    public boolean includes(LocalDateTime dateTime) {
        return startDate.isBefore(dateTime) && endDate.isAfter(dateTime);
    }

    public boolean isIncludedIn(TimeLineModel timeLineModel) {
        return timeLineModel.includes(startDate) && timeLineModel.includes(endDate);
    }

}
