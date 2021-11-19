package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.repositories.events.OnNewEventListening;
import com.lemonado.smartmeet.core.repositories.events.OnUpdateEventListening;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository {

    @OnNewEventListening
    GroupModel save(GroupModel groupModel);

    @OnUpdateEventListening
    GroupModel update(GroupModel groupModel);

    boolean existsById(long id);

    boolean existsInGroup(long groupId, long userId);

    Optional<GroupModel> getGroupById(long id);

    Optional<GroupModel> getGroupByCode(String code);

    Set<GroupModel> getGroupsByUser(long userId);

}
