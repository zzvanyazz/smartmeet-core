package com.lemonado.smartmeet.core.services.validation.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import com.lemonado.smartmeet.core.services.base.groups.GroupUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GroupUsersServiceValidation implements GroupUsersService {

    @Autowired
    private GroupServiceValidation groupService;

    private final GroupUsersService groupUsersService;

    public GroupUsersServiceValidation(GroupUsersService groupUsersService) {
        this.groupUsersService = groupUsersService;
    }

    @Override
    public GroupUserModel getGroupUser(long groupId, long userId)
            throws InvalidGroupException {
        return groupUsersService.getGroupUser(groupId, userId);
    }

    @Override
    public GroupModel registerUserToGroup(long userId, String code)
            throws InvalidGroupException, UserNotFoundException, UnsupportedGroupException {
        var groupId = groupService.getGroupByCode(code).id();
        groupService.assertCreator(groupId, userId);

        return groupUsersService.registerUserToGroup(userId, code);
    }

    @Override
    public void removeUsers(long groupId, Set<Long> users) throws InvalidGroupException {
        clenIds(groupId, users);
        groupUsersService.removeUsers(groupId, users);
    }

    @Override
    public void renewUsers(long groupId, Set<Long> users) throws InvalidGroupException {
        clenIds(groupId, users);
        groupUsersService.renewUsers(groupId, users);
    }

    private void clenIds(long groupId, Set<Long> users) {
        users.removeIf(id -> {
            try {
                return !existsInGroup(groupId, id);
            } catch (InvalidGroupException | UserNotFoundException ignored) {
                return true;
            }
        });
    }

    @Override
    public boolean existsInGroup(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException {
        return groupUsersService.existsInGroup(groupId, userId);
    }

    @Override
    public void assertExistsInGroup(long groupId, long userId)
            throws InvalidGroupException, UnsupportedGroupException, UserNotFoundException {
        groupUsersService.assertExistsInGroup(groupId, userId);
    }


}
