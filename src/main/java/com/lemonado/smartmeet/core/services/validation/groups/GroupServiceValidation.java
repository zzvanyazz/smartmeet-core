package com.lemonado.smartmeet.core.services.validation.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupModelBuilder;
import com.lemonado.smartmeet.core.options.SecureOptions;
import com.lemonado.smartmeet.core.repositories.GroupRepository;
import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.validation.secure.SecureRandomService;
import com.lemonado.smartmeet.core.services.validation.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Service
public class GroupServiceValidation implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserServiceImpl userService;

    private final GroupService groupService;

    public GroupServiceValidation(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public GroupModel createGroup(long creatorId, @NotNull String name)
            throws NoSuchAlgorithmException, UserNotFoundException {
        //TODO: check name
        return groupService.createGroup(creatorId, name);
    }

    @Override
    public GroupModel updateGroupName(long groupId, String name) throws InvalidGroupException {
        //TODO: check name
        return groupService.updateGroupName(groupId, name);
    }

    @Override
    public GroupModel updateGroupCode(long groupId)
            throws InvalidGroupException, NoSuchAlgorithmException {
        return groupService.updateGroupCode(groupId);
    }

    @Override
    public GroupModel getGroup(long groupId)
            throws InvalidGroupException {
        return groupService.getGroup(groupId);
    }

    @Override
    public GroupModel getGroupByCode(String code) throws InvalidGroupException {
        return groupService.getGroupByCode(code);
    }

    @Override
    public Set<GroupModel> getGroupsByUser(long userId) throws UserNotFoundException {
        userService.assertExists(userId);

        return groupService.getGroupsByUser(userId);
    }

    @Override
    public void assertExists(long id) throws InvalidGroupException {
        if (!groupRepository.existsById(id))
            throw new InvalidGroupException();
    }

    @Override
    public boolean existsInGroup(long groupId, long userId) {
        return groupService.existsInGroup(groupId, userId);
    }

    @Override
    public void assertCreator(long groupId, long userId)
            throws UnsupportedGroupException, InvalidGroupException, UserNotFoundException {
        assertExists(groupId);
        userService.assertExists(userId);
        if (!existsInGroup(groupId, userId))
            throw new UnsupportedGroupException();
    }

}
