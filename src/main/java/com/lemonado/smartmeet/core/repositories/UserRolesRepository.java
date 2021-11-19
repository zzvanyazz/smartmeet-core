package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.users.UserRoleModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository {

    List<UserRoleModel> findByUserId(long userId);

    void saveAssign(long userId, long roleId);

    void removeAssign(long userId, long roleId);

    boolean isExists(long userId, long roleId);

}
