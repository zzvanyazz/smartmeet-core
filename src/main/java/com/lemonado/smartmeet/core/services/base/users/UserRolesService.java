package com.lemonado.smartmeet.core.services.base.users;

import com.lemonado.smartmeet.core.data.exceptions.ActionOnAdminRoleException;
import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserRoleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRolesService {

    List<UserRoleModel> getUserRoles(long userId) throws UserNotFoundException;

    void assignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException;

    void removeAssignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException;

    List<RoleModel> getRoles(long userId) throws UserNotFoundException, RoleNotFoundException;

}
