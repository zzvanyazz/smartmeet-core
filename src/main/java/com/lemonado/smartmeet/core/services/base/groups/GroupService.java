package com.lemonado.smartmeet.core.services.base.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Service
public interface GroupService {


    GroupModel createGroup(long creatorId, @NotNull String name)
            throws NoSuchAlgorithmException, UserNotFoundException;

    GroupModel updateGroupName(long groupId, String name) throws InvalidGroupException;

    GroupModel updateGroupCode(long groupId)
            throws InvalidGroupException, NoSuchAlgorithmException;

    GroupModel getGroup(long groupId)
            throws InvalidGroupException;

    GroupModel getGroupByCode(String code) throws InvalidGroupException;

    Set<GroupModel> getGroupsByUser(long userId) throws UserNotFoundException;

    void assertExists(long id) throws InvalidGroupException;

    boolean existsInGroup(long groupId, long userId);

    void assertCreator(long groupId, long userId)
            throws UnsupportedGroupException, InvalidGroupException, UserNotFoundException;


}
