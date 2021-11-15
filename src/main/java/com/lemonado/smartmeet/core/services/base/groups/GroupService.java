package com.lemonado.smartmeet.core.services.base.groups;

import com.lemonado.smartmeet.core.data.exceptions.CanNotCreateGroupException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.GroupNameAlreadyExists;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GroupService {


    /**
     * Creating new group for special user
     *
     * @param creatorId id of a user which initiates creation
     * @param name      name of new group
     * @throws CanNotCreateGroupException Can not create registration code by invalid algorithm of creating code.
     * @throws UserNotFoundException      Can not find creator by id.
     */
    GroupModel createGroup(long creatorId, String name)
            throws UserNotFoundException, CanNotCreateGroupException;

    /**
     * Changing group name by group id
     *
     * @param groupId id of group
     * @param name    new name of group
     * @throws InvalidGroupException  Group by id not found.
     * @throws GroupNameAlreadyExists Group with such name already exists.
     */
    GroupModel updateGroupName(long groupId, String name)
            throws InvalidGroupException, GroupNameAlreadyExists;

    /**
     * Changing group registration code
     *
     * @param groupId id of group
     * @throws InvalidGroupException      Group by id not found.
     * @throws CanNotCreateGroupException Can not create registration code by invalid algorithm of creating code.
     */
    GroupModel updateGroupCode(long groupId)
            throws InvalidGroupException, CanNotCreateGroupException;

    /**
     * Changing group registration code
     *
     * @param groupId id of group
     * @throws InvalidGroupException      Group by id not found.
     */
    GroupModel getGroup(long groupId)
            throws InvalidGroupException;
    
    GroupModel getGroupByCode(String code) throws InvalidGroupException;

    Set<GroupModel> getGroupsByUser(long userId) throws UserNotFoundException;

    void assertExists(long id) throws InvalidGroupException;

    boolean existsInGroup(long groupId, long userId);

    void assertCreator(long groupId, long userId)
            throws UnsupportedGroupException, InvalidGroupException, UserNotFoundException;


}
