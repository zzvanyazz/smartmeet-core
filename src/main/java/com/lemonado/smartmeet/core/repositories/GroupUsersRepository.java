package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import com.lemonado.smartmeet.core.repositories.events.OnDeleteEventListening;
import com.lemonado.smartmeet.core.repositories.events.OnNewEventListening;
import com.lemonado.smartmeet.core.repositories.events.OnUpdateEventListening;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupUsersRepository {

    @OnNewEventListening
    GroupUserModel save(GroupUserModel groupUserModel);

    @OnUpdateEventListening
    GroupUserModel update(GroupUserModel groupUserModel);

    @OnDeleteEventListening
    GroupUserModel remove(GroupUserModel groupUserModel);

    Optional<GroupUserModel> getByGroupAndUser(long groupId, long userId);

    List<GroupUserModel> findAllByGroup(long groupId);

    List<GroupUserModel> findAllByUser(long userId);

}
