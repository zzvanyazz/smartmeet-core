package com.lemonado.smartmeet.core.services.validation.users;

import com.lemonado.smartmeet.core.data.exceptions.ActionOnAdminRoleException;
import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserRoleModel;
import com.lemonado.smartmeet.core.services.base.users.RoleService;
import com.lemonado.smartmeet.core.services.base.users.UserRolesService;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesServiceValidation implements UserRolesService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private final UserRolesService userRolesService;

    public UserRolesServiceValidation(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @Override
    public List<UserRoleModel> getUserRoles(long userId) throws UserNotFoundException {
        userService.assertExists(userId);

        return userRolesService.getUserRoles(userId);
    }

    @Override
    public void assignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException {
        ensureAllowed(userId, roleId);

        userRolesService.assignUserRole(userId, roleId);
    }

    @Override
    public void removeAssignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException {
        ensureAllowed(userId, roleId);

        userRolesService.removeAssignUserRole(userId, roleId);
    }

    @Override
    public List<RoleModel> getRoles(long userId)
            throws UserNotFoundException, RoleNotFoundException {
        return userRolesService.getRoles(userId);
    }

    private void ensureAllowed(long userId, long roleId)
            throws RoleNotFoundException, ActionOnAdminRoleException, UserNotFoundException {
        userService.assertExists(userId);
        roleService.ensureExists(roleId);
        var role = roleService.getById(roleId);
        if (role.name().equals(RoleModel.ADMIN))
            throw new ActionOnAdminRoleException();
    }

}
