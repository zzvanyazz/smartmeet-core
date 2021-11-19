package com.lemonado.smartmeet.core.services.base.timeline;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.exceptions.timeline.InvalidTimeLineException;
import com.lemonado.smartmeet.core.data.models.timeline.TimeLineModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TimeLineService {

    List<TimeLineModel> getTimeLines(long groupId) throws InvalidGroupException;

    List<TimeLineModel> getTimeLines(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException, UnsupportedGroupException;

    TimeLineModel updateTimeLine(TimeLineModel timeLine);

    void removeTimeLine(TimeLineModel timeLine);

    TimeLineModel addNewTimeLine(TimeLineModel timeLine)
            throws UserNotFoundException, InvalidGroupException,
            UnsupportedGroupException, InvalidTimeLineException;

}
