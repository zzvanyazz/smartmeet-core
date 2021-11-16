package com.lemonado.smartmeet.core.services.validation.timeline;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.exceptions.timeline.InvalidTimeLineException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.base.groups.GroupUsersService;
import com.lemonado.smartmeet.core.services.base.timeline.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeLineServiceValidation implements TimeLineService {

    @Autowired
    private GroupUsersService groupUsersService;

    @Autowired
    private GroupService groupService;

    private final TimeLineService timeLineService;

    public TimeLineServiceValidation(TimeLineService timeLineService) {
        this.timeLineService = timeLineService;
    }

    @Override
    public List<TimeLineModel> getTimeLines(long groupId) throws InvalidGroupException {
        groupService.assertExists(groupId);

        return timeLineService.getTimeLines(groupId);
    }

    @Override
    public List<TimeLineModel> getTimeLines(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException, UnsupportedGroupException {

        groupUsersService.assertExistsInGroup(groupId, userId);

        return timeLineService.getTimeLines(groupId, userId);
    }

    @Override
    public TimeLineModel addNewTimeLine(TimeLineModel timeLine)
            throws UserNotFoundException, InvalidGroupException, UnsupportedGroupException, InvalidTimeLineException {
        var timeLineOptional = Optional.of(timeLine);

        var groupId = timeLineOptional
                .map(TimeLineModel::groupModel)
                .map(GroupModel::id)
                .orElseThrow(InvalidTimeLineException::new);

        var userId = timeLineOptional
                .map(TimeLineModel::groupModel)
                .map(GroupModel::id)
                .orElseThrow(InvalidTimeLineException::new);

        groupUsersService.assertExistsInGroup(groupId, userId);

        return timeLineService.addNewTimeLine(timeLine);
    }

}
