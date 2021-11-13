package com.lemonado.smartmeet.core.services.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupModelBuilder;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import com.lemonado.smartmeet.core.options.SecureOptions;
import com.lemonado.smartmeet.core.repositories.GroupRepository;
import com.lemonado.smartmeet.core.services.secure.SecureRandomService;
import com.lemonado.smartmeet.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private SecureOptions secureOptions;

    @Autowired
    private SecureRandomService secureRandomService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public GroupModel createGroup(long creatorId, @NotNull String name)
            throws NoSuchAlgorithmException, UserNotFoundException {
        var creator = userService.getUser(creatorId);

        var code = generateInviteCode();
        var groupModel = GroupModelBuilder.builder()
                .withCode(code)
                .withCreator(creator)
                .withName(name)
                .build();

        return groupRepository.save(groupModel);
    }

    public GroupModel updateGroupName(long groupId, String name) throws InvalidGroupException {
        var group = getGroupModel(groupId);

        group = GroupModelBuilder.from(group)
                .withName(name)
                .build();
        return groupRepository.save(group);
    }

    public GroupModel updateGroupCode(long groupId) throws InvalidGroupException, NoSuchAlgorithmException {
        var group = getGroupModel(groupId);
        var code = generateInviteCode();
        group = GroupModelBuilder.from(group)
                .withCode(code)
                .build();
        return groupRepository.save(group);
    }

    public GroupModel registerUserToGroup(long userId, String code)
            throws InvalidGroupException, UserNotFoundException {
        var user = userService.getUser(userId);
        var groupModel = groupRepository.getGroupByCode(code)
                .orElseThrow(InvalidGroupException::new);
        if (!groupModel.users().contains(user)) {
            groupModel.users().add(user);
            groupModel = groupRepository.update(groupModel);
        }
        return groupModel;
    }

    public GroupModel getGroupModel(long groupId)
            throws InvalidGroupException{
        return groupRepository.getGroupById(groupId)
                .orElseThrow(InvalidGroupException::new);
    }

    public List<GroupModel> getGroupsByUser(long userId) throws UserNotFoundException {
        userService.assertExists(userId);
        return groupRepository.getGroupsByUser(userId);
    }

    public void assertExists(long id) throws InvalidGroupException {
        if (!groupRepository.existsById(id))
            throw new InvalidGroupException();
    }

    public void assertExistsInGroup(long groupId, long userId)
            throws UnsupportedGroupException, InvalidGroupException, UserNotFoundException {
        assertExists(groupId);
        userService.assertExists(userId);
        if (!groupRepository.existsInGroup(groupId, userId))
            throw new UnsupportedGroupException();
    }

    private String generateInviteCode() throws NoSuchAlgorithmException {
        var length = secureOptions.getGroupCodeLength();
        return secureRandomService.generateAlphaNumeric(length);
    }

}
