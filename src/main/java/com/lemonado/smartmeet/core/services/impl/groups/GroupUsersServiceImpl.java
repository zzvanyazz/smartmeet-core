package com.lemonado.smartmeet.core.services.impl.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.AddedUserStatus;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import com.lemonado.smartmeet.core.data.models.group.builder.GroupUserBuilder;
import com.lemonado.smartmeet.core.repositories.GroupUsersRepository;
import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.base.groups.GroupUsersService;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service
public class GroupUsersServiceImpl implements GroupUsersService {

    @Autowired
    private GroupUsersRepository groupUsersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    @Override
    public GroupUserModel getGroupUser(long groupId, long userId) throws InvalidGroupException {
        return groupUsersRepository.getByGroupAndUser(groupId, userId)
                .orElseThrow(InvalidGroupException::new);
    }

    @Override
    public GroupModel registerUserToGroup(long userId, String code)
            throws InvalidGroupException, UserNotFoundException {
        var user = userService.getUser(userId);
        var group = groupService.getGroupByCode(code);
        var groupId = group.id();

        if (!existsInGroup(userId, groupId)) {
            var groupUser = GroupUserBuilder.builder()
                    .withUser(user)
                    .withStatus(AddedUserStatus.VALID)
                    .withGroupModel(group)
                    .withInviteTime(LocalDateTime.now())
                    .build();
            groupUsersRepository.save(groupUser);
        }
        return groupService.getGroup(groupId);
    }

    @Override
    public void removeUsers(long groupId, Set<Long> users) {
        process(groupId, users, AddedUserStatus.REMOVED);
    }

    @Override
    public void renewUsers(long groupId, Set<Long> users) {
        process(groupId, users, AddedUserStatus.VALID);
    }

    public void process(long groupId, Set<Long> users, AddedUserStatus status) {
        users.stream()
                .map(id -> getGroupUserSafe(groupId, id))
                .filter(Objects::nonNull)
                .map(groupUser -> GroupUserBuilder.from(groupUser).withStatus(status).build())
                .forEach(groupUsersRepository::save);
    }

    private GroupUserModel getGroupUserSafe(long groupId, long id) {
        try {
            return getGroupUser(groupId, id);
        } catch (InvalidGroupException e) {
            return null;
        }
    }

    @Override
    public boolean existsInGroup(long groupId, long userId) throws InvalidGroupException, UserNotFoundException {
        var user = userService.getUser(userId);
        var group = groupService.getGroup(groupId);
        return group.users().stream()
                .filter(groupUserModel -> groupUserModel.status() == AddedUserStatus.VALID)
                .map(GroupUserModel::user)
                .anyMatch(user::equals);
    }

    @Override
    public void assertExistsInGroup(long groupId, long userId)
            throws InvalidGroupException, UnsupportedGroupException, UserNotFoundException {
        if (existsInGroup(groupId, userId)) {
            throw new UnsupportedGroupException();
        }
    }


}
