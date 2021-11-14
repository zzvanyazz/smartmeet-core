package com.lemonado.smartmeet.core.services.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.AddedUserStatus;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import com.lemonado.smartmeet.core.data.models.group.builder.GroupUserBuilder;
import com.lemonado.smartmeet.core.repositories.GroupUsersRepository;
import com.lemonado.smartmeet.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class GroupUsersService {

    @Autowired
    private GroupUsersRepository groupUsersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public GroupUserModel getGroupUser(long groupId, long userId)
            throws InvalidGroupException {
        return groupUsersRepository.getByGroupAndUser(groupId, userId)
                .orElseThrow(InvalidGroupException::new);
    }

    public GroupModel registerUserToGroup(long userId, String code)
            throws InvalidGroupException, UserNotFoundException {
        var user = userService.getUser(userId);
        var group = groupService.getGroupByCode(code);

        if (!existsInGroup(userId, group.id())) {
            var groupUser = GroupUserBuilder.builder()
                    .withUser(user)
                    .withStatus(AddedUserStatus.VALID)
                    .withGroupModel(group)
                    .withInviteTime(LocalDateTime.now())
                    .build();
            groupUsersRepository.save(groupUser);
        }
        return group;
    }

    public void removeUsers(long groupId, Set<Long> users) throws InvalidGroupException {
        users.stream()
                .filter(id -> existsInGroup(groupId, id))
                .map(id -> getGroupUser(groupId, id))
                .map(groupUser -> GroupUserBuilder.from(groupUser)
                        .withStatus(AddedUserStatus.REMOVED)
                        .build())
                .forEach(groupUsersRepository::save);
    }

    public boolean existsInGroup(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException {
        var user = userService.getUser(userId);
        var group = groupService.getGroup(groupId);
        return group.users().stream()
                .filter(groupUserModel -> groupUserModel.status() == AddedUserStatus.VALID)
                .map(GroupUserModel::user)
                .anyMatch(user::equals);
    }

    public void assertExistsInGroup(long groupId, long userId)
            throws InvalidGroupException, UnsupportedGroupException, UserNotFoundException {
        if (existsInGroup(groupId, userId))
            throw new UnsupportedGroupException();
    }


}
