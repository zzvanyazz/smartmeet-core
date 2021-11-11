package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineRepository {

    List<TimeLineModel> getTimeLines(long groupId, long userId);

    List<TimeLineModel> getTimeLines(long groupId);

    TimeLineModel addTimeLine(TimeLineModel model);

}
