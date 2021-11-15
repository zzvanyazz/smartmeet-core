package com.lemonado.smartmeet.core.services.impl.timeline;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.data.models.timeline.bilders.TimeLineBuilder;
import com.lemonado.smartmeet.core.repositories.TimeLineRepository;
import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.base.groups.GroupUsersService;
import com.lemonado.smartmeet.core.services.base.timeline.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired
    private TimeLineRepository timeLineRepository;


    @Override
    public List<TimeLineModel> getTimeLines(long groupId) {
        return timeLineRepository.findByGroup(groupId);
    }

    @Override
    public List<TimeLineModel> getTimeLines(long groupId, long userId) {

        return timeLineRepository.findByGroupAndUser(groupId, userId);
    }

    @Override
    public TimeLineModel addNewTimeLine(TimeLineModel newTimeLine) {
        var groupId = newTimeLine.groupModel().id();
        var userId = newTimeLine.user().id();

        var timeLines = getTimeLines(groupId, userId)
                .stream()
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
