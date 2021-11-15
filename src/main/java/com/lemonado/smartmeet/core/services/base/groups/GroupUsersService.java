package com.lemonado.smartmeet.core.services.base.groups;

import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.group.InvalidGroupException;
import com.lemonado.smartmeet.core.data.exceptions.group.UnsupportedGroupException;
import com.lemonado.smartmeet.core.data.models.group.AddedUserStatus;
import com.lemonado.smartmeet.core.data.models.group.GroupModel;
import com.lemonado.smartmeet.core.data.models.group.GroupUserModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GroupUsersService {

    GroupUserModel getGroupUser(long groupId, long userId)
            throws InvalidGroupException;

    GroupModel registerUserToGroup(long userId, String code)
            throws InvalidGroupException, UserNotFoundException;

    void removeUsers(long groupId, Set<Long> users)
            throws InvalidGroupException;

    void renewUsers(long groupId, Set<Long> users)
            throws InvalidGroupException;

    boolean existsInGroup(long groupId, long userId)
            throws InvalidGroupException, UserNotFoundException;

    void assertExistsInGroup(long groupId, long userId)
            throws InvalidGroupException, UnsupportedGroupException, UserNotFoundException;

}
