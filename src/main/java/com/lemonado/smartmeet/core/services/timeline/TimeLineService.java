package com.lemonado.smartmeet.core.services.timeline;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.repositories.TimeLineRepository;
import com.lemonado.smartmeet.core.services.groups.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeLineService {

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private GroupService groupService;

    public List<TimeLineModel> getTimeLines(long groupId) throws InvalidGroupException {
        groupService.assertExists(groupId);
        return timeLineRepository.getTimeLines(groupId);
    }

    public List<TimeLineModel> getTimeLines(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException, UnsupportedGroupException {
        groupService.assertExistsInGroup(groupId, userId);
        return timeLineRepository.getTimeLines(groupId, userId);
    }

    public TimeLineModel addNewTimeLine(TimeLineModel timeLine)
            throws UserNotFoundException, InvalidGroupException, UnsupportedGroupException {
        var groupId = timeLine.groupModel().id();
        var userId = timeLine.user().id();
        groupService.assertExistsInGroup(groupId, userId);
        return timeLineRepository.addTimeLine(timeLine);
    }

}
