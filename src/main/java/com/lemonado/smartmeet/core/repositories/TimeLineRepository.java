package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineRepository {

    List<TimeLineModel> findByGroupAndUser(long groupId, long userId);

    List<TimeLineModel> findByGroup(long groupId);

    void remove(TimeLineModel timeLineModel);

    TimeLineModel update(TimeLineModel timeLineModel);

    TimeLineModel save(TimeLineModel model);

}
