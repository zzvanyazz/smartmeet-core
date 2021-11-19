package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import com.lemonado.smartmeet.core.repositories.events.OnDeleteEventListening;
import com.lemonado.smartmeet.core.repositories.events.OnNewEventListening;
import com.lemonado.smartmeet.core.repositories.events.OnUpdateEventListening;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineRepository {

    List<TimeLineModel> findByGroupAndUser(long groupId, long userId);

    List<TimeLineModel> findByGroup(long groupId);

    @OnNewEventListening
    void remove(TimeLineModel timeLineModel);

    @OnUpdateEventListening
    TimeLineModel update(TimeLineModel timeLineModel);

    @OnDeleteEventListening
    TimeLineModel save(TimeLineModel timeLineModel);

}
