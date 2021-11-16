package com.lemonado.smartmeet.core.services.impl.users;

import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserRoleModel;
import com.lemonado.smartmeet.core.repositories.UserRolesModelRepository;
import com.lemonado.smartmeet.core.services.base.users.RoleService;
import com.lemonado.smartmeet.core.services.base.users.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesModelRepository userRolesRepository;

    @Autowired
    private RoleService roleService;


    @Override
    public List<UserRoleModel> getUserRoles(long userId) {
        return userRolesRepository.findByUserId(userId);
    }

    @Override
    public void assignUserRole(long userId, long roleId) {
        if (!userRolesRepository.isExists(userId, roleId)) {
            userRolesRepository.saveAssign(userId, roleId);
        }
    }

    @Override
    public void removeAssignUserRole(long userId, long roleId) {
        if (userRolesRepository.isExists(userId, roleId)) {
            userRolesRepository.removeAssign(userId, roleId);
        }
    }

    @Override
    public List<RoleModel> getRoles(long userId) throws RoleNotFoundException {
        var roleIdList =  getUserRoles(userId)
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

}
