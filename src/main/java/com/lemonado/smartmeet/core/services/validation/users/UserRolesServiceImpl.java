package com.lemonado.smartmeet.core.services.validation.users;

import com.lemonado.smartmeet.core.data.exceptions.ActionOnAdminRoleException;
import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserRoleModel;
import com.lemonado.smartmeet.core.repositories.UserRolesModelRepository;
import com.lemonado.smartmeet.core.services.base.users.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesModelRepository userRolesModelRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public List<UserRoleModel> streamUserRoles(long userId) throws UserNotFoundException {
        userService.assertExists(userId);
        return userRolesModelRepository.findByUserId(userId);
    }

    @Override
    public void assignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException {
        userService.assertExists(userId);
        roleService.ensureExists(roleId);
        ensureAllowed(roleId);
        if (!userRolesModelRepository.isExists(userId, roleId))
            userRolesModelRepository.saveAssign(userId, roleId);
    }

    @Override
    public void removeAssignUserRole(long userId, long roleId)
            throws UserNotFoundException, RoleNotFoundException, ActionOnAdminRoleException {
        userService.assertExists(userId);
        roleService.ensureExists(roleId);
        ensureAllowed(roleId);
        if (userRolesModelRepository.isExists(userId, roleId))
            userRolesModelRepository.removeAssign(userId, roleId);
    }

    @Override
    public List<RoleModel> getUserRoles(long userId) throws UserNotFoundException, RoleNotFoundException {
        var roleIdList =  streamUserRoles(userId)
                .stream()
                .map(UserRoleModel::roleId)
                .collect(Collectors.toList());
        var userRoles = new ArrayList<RoleModel>();

        for (Long id: roleIdList) {
            var role = roleService.getById(id);
            userRoles.add(role);
        }
        return userRoles;
    }

    private void ensureAllowed(long roleId)
            throws RoleNotFoundException, ActionOnAdminRoleException {
        var role = roleService.getById(roleId);
        if (role.name().equals(RoleModel.ADMIN))
            throw new ActionOnAdminRoleException();
    }

}
