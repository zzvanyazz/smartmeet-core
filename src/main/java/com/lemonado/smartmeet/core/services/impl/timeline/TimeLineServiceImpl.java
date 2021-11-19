package com.lemonado.smartmeet.core.services.impl.timeline;

import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.data.models.timeline.bilders.TimeLineBuilder;
import com.lemonado.smartmeet.core.repositories.TimeLineRepository;
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
    public TimeLineModel updateTimeLine(TimeLineModel timeLine) {
        return addNewTimeLine(timeLine);
    }

    @Override
    public void removeTimeLine(TimeLineModel timeLine) {
        timeLineRepository.remove(timeLine);
    }

    @Override
    public TimeLineModel addNewTimeLine(TimeLineModel newTimeLine) {
        var groupId = newTimeLine.groupModel().id();
        var userId = newTimeLine.user().id();

        var timeLines = getTimeLines(groupId, userId);
        timeLines.removeIf(timeLine -> {
            if (timeLine.isIncludedIn(newTimeLine)) {
                removeTimeLine(newTimeLine);
                return true;
            }
            return false;
        });
        timeLines = timeLines
                .stream()
                .filter(newTimeLine::intersects)
                .sorted(Comparator.comparing(TimeLineModel::startDate))
                .collect(Collectors.toList());

        if (!timeLines.isEmpty()) {
            resolveIntersect(timeLines, newTimeLine);
        }
        return timeLineRepository.save(newTimeLine);
    }

    private void resolveIntersect(List<TimeLineModel> intersected, TimeLineModel newTimeLine) {
        var firstIntersect = intersected.remove(0);
        if (intersected.isEmpty()) {
            resolveOneIntersected(firstIntersect, newTimeLine);
            return;
        }

        var lastIntersect = intersected.remove(intersected.size() - 1);

        var firstTimeLine = TimeLineBuilder.from(firstIntersect)
                .withEndDate(newTimeLine.startDate())
                .build();

        var endTimeLine = TimeLineBuilder.from(lastIntersect)
                .withStartDate(newTimeLine.endDate())
                .build();

        intersected.forEach(timeLineRepository::remove);

        updateTimeLine(firstTimeLine);
        updateTimeLine(endTimeLine);
    }

    private void resolveOneIntersected(TimeLineModel intersected, TimeLineModel newTimeLine) {
        if (intersected.sameRange(newTimeLine) || intersected.isIncludedIn(newTimeLine)) {
            removeTimeLine(intersected);
        } else if (newTimeLine.isIncludedIn(intersected)) {
            var firstTimeLine = TimeLineBuilder.from(intersected)
                    .withoutId()
                    .withEndDate(newTimeLine.startDate())
                    .build();
            var endTimeLine = TimeLineBuilder.from(intersected)
                    .withoutId()
                    .withStartDate(newTimeLine.endDate())
                    .build();
            removeTimeLine(intersected);

            addNewTimeLine(firstTimeLine);
            addNewTimeLine(endTimeLine);
        } else {
            resolveIntersected(intersected, newTimeLine);
        }
    }

    private void resolveIntersected(TimeLineModel intersected, TimeLineModel newTimeLine) {
        var intersectedUpdater = TimeLineBuilder.from(intersected);
        if (newTimeLine.includes(intersected.startDate())) {
            intersectedUpdater.withStartDate(newTimeLine.endDate());
        } else if (newTimeLine.includes(newTimeLine.endDate())) {
            intersectedUpdater.withEndDate(newTimeLine.startDate());
        }
        intersected = intersectedUpdater.build();
        addNewTimeLine(intersected);
    }

}
