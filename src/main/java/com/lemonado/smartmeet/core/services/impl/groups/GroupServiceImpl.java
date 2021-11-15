package com.lemonado.smartmeet.core.services.impl.groups;

import com.lemonado.smartmeet.core.data.exceptions.CanNotCreateGroupException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.builder.GroupModelBuilder;
import com.lemonado.smartmeet.core.options.SecureOptions;
import com.lemonado.smartmeet.core.repositories.GroupRepository;
import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import com.lemonado.smartmeet.core.services.impl.secure.SecureRandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private SecureOptions secureOptions;

    @Autowired
    private SecureRandomService secureRandomService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;


    @Override
    public GroupModel createGroup(long creatorId, @NotNull String name)
            throws CanNotCreateGroupException, UserNotFoundException {
        var creator = userService.getUser(creatorId);

        var code = generateInviteCode();
        var groupModel = GroupModelBuilder.builder()
                .withCode(code)
                .withCreator(creator)
                .withName(name)
                .build();

        return groupRepository.save(groupModel);
    }

    @Override
    public GroupModel updateGroupName(long groupId, String name) throws InvalidGroupException {
        var group = getGroup(groupId);

        group = GroupModelBuilder.from(group)
                .withName(name)
                .build();
        return groupRepository.save(group);
    }

    @Override
    public GroupModel updateGroupCode(long groupId)
            throws InvalidGroupException, CanNotCreateGroupException {
        var group = getGroup(groupId);
        var code = generateInviteCode();
        group = GroupModelBuilder.from(group)
                .withCode(code)
                .build();
        return groupRepository.save(group);
    }

    @Override
    public GroupModel getGroup(long groupId)
            throws InvalidGroupException {
        return groupRepository.getGroupById(groupId)
                .orElseThrow(InvalidGroupException::new);
    }

    @Override
    public GroupModel getGroupByCode(String code) throws InvalidGroupException {
        return groupRepository.getGroupByCode(code)
                .orElseThrow(InvalidGroupException::new);
    }

    @Override
    public Set<GroupModel> getGroupsByUser(long userId) throws UserNotFoundException {
        userService.assertExists(userId);
        return groupRepository.getGroupsByUser(userId);
    }

    @Override
    public void assertExists(long id) throws InvalidGroupException {
        if (!groupRepository.existsById(id))
            throw new InvalidGroupException();
    }

    @Override
    public boolean existsInGroup(long groupId, long userId) {
        return groupRepository.existsInGroup(groupId, userId);
    }

    @Override
    public void assertCreator(long groupId, long userId)
            throws UnsupportedGroupException, InvalidGroupException, UserNotFoundException {
        assertExists(groupId);
        userService.assertExists(userId);
        if (!existsInGroup(groupId, userId))
            throw new UnsupportedGroupException();
    }

    private String generateInviteCode() throws CanNotCreateGroupException {
        var length = secureOptions.getGroupCodeLength();
        try {
            return secureRandomService.generateAlphaNumeric(length);
        } catch (NoSuchAlgorithmException e) {
            throw new CanNotCreateGroupException();
        }
    }


}
