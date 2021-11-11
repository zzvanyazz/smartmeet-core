package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository {

    GroupModel safe(GroupModel groupModel);

    boolean existsById(long id);

    boolean existsInGroup(long groupId, long userId);

    Optional<GroupModel> getGroupById(long id);

    Optional<GroupModel> getGroupByCode(String code);

    GroupModel update(GroupModel groupModel);

    List<GroupModel> getGroupsByUser(long userId);

}
