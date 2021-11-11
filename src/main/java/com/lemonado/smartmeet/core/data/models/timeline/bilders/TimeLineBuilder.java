package com.lemonado.smartmeet.core.data.models.timeline.bilders;

import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineType;
import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.LocalDateTime;

public class TimeLineBuilder {

    private long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private GroupModel groupModel;
    private UserModel user;
    private TimeLineType timeLineType;


    public static TimeLineBuilder builder() {
        return new TimeLineBuilder();
    }

    public TimeLineBuilder() {
    }

    public TimeLineBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public TimeLineBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public TimeLineBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public TimeLineBuilder setGroupModel(GroupModel groupModel) {
        this.groupModel = groupModel;
        return this;
    }

    public TimeLineBuilder withUser(UserModel user) {
        this.user = user;
        return this;
    }

    public TimeLineBuilder withTimeLineType(TimeLineType timeLineType) {
        this.timeLineType = timeLineType;
        return this;
    }

    public TimeLineModel build() {
        return new TimeLineModel(id, startDate, endDate, groupModel, user, timeLineType);
    }
}
