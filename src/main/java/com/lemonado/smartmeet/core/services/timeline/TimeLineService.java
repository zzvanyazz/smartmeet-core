package com.lemonado.smartmeet.core.services.timeline;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.data.models.timeline.bilders.TimeLineBuilder;
import com.lemonado.smartmeet.core.repositories.TimeLineRepository;
import com.lemonado.smartmeet.core.services.groups.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeLineService {

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private GroupService groupService;

    public List<TimeLineModel> getTimeLines(long groupId) throws InvalidGroupException {
        groupService.assertExists(groupId);
        return timeLineRepository.findByGroup(groupId);
    }

    public List<TimeLineModel> getTimeLines(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException, UnsupportedGroupException {
        groupService.assertExistsInGroup(groupId, userId);
        return timeLineRepository.findByGroupAndUser(groupId, userId);
    }

    public TimeLineModel addNewTimeLine(TimeLineModel timeLine)
            throws UserNotFoundException, InvalidGroupException, UnsupportedGroupException {
        var groupId = timeLine.groupModel().id();
        var userId = timeLine.user().id();
        groupService.assertExistsInGroup(groupId, userId);
        return addNewTimeLineSafe(timeLine);
    }

    private TimeLineModel addNewTimeLineSafe(TimeLineModel newTimeLine)
            throws UserNotFoundException, InvalidGroupException, UnsupportedGroupException {
        var groupId = newTimeLine.groupModel().id();
        var userId = newTimeLine.user().id();
        var timeLines = getTimeLines(groupId, userId).stream()
                .filter(newTimeLine::intersects)
                .sorted(Comparator.comparing(TimeLineModel::startDate))
                .collect(Collectors.toList());

        if (!timeLines.isEmpty())
            resolveIntersect(timeLines, newTimeLine);
        return timeLineRepository.save(newTimeLine);
    }

    private void resolveIntersect(List<TimeLineModel> intersected, TimeLineModel newTimeLine) {
        var firstIntersectTimeLine = intersected.remove(0);
        var lastIntersectTimeLine = intersected.remove(intersected.size() - 1);

        var firstTimeLine = TimeLineBuilder.from(firstIntersectTimeLine)
                .withoutId()
                .withEndDate(newTimeLine.startDate())
                .build();

        var endTimeLine = TimeLineBuilder.from(lastIntersectTimeLine)
                .withoutId()
                .withStartDate(newTimeLine.endDate())
                .build();

        intersected.forEach(timeLineRepository::remove);

        timeLineRepository.update(firstTimeLine);
        timeLineRepository.update(endTimeLine);
    }

}
