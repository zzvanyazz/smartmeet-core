package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupUsersRepository {

    GroupUserModel save(GroupUserModel userModel);

    GroupUserModel remove(GroupUserModel groupUserModel);

    Optional<GroupUserModel> getByGroupAndUser(long groupId, long userId);

    List<GroupUserModel> findAllByGroup(long groupId);

    List<GroupUserModel> findAllByUser(long userId);

}
