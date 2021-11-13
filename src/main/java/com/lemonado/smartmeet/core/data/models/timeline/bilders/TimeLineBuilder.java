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

    public static TimeLineBuilder from(TimeLineModel timeLineModel) {
        return new TimeLineBuilder(timeLineModel);
    }


    public TimeLineBuilder(TimeLineModel timeLineModel) {
        this.id = timeLineModel.id();
        this.startDate = timeLineModel.startDate();
        this.endDate = timeLineModel.endDate();
        this.groupModel = timeLineModel.groupModel();
        this.user = timeLineModel.user();
        this.timeLineType = timeLineModel.timeLineType();
    }

    public TimeLineBuilder() {
    }

    public TimeLineBuilder withoutId() {
        this.id = 0;
        return this;
    }

    public TimeLineBuilder withId(long id) {
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

    public TimeLineBuilder withGroupModel(GroupModel groupModel) {
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
