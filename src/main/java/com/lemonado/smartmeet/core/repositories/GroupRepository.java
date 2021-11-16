package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository {

    GroupModel save(GroupModel groupModel);

    boolean existsById(long id);

    boolean existsInGroup(long groupId, long userId);

    Optional<GroupModel> getGroupById(long id);

    Optional<GroupModel> getGroupByCode(String code);

    GroupModel update(GroupModel groupModel);

    Set<GroupModel> getGroupsByUser(long userId);

}
