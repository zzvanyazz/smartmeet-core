package com.lemonado.smartmeet.core.services.base.users;

import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<RoleModel> getAll();

    long findId(String name) throws RoleNotFoundException;

    RoleModel getById(long id) throws RoleNotFoundException;

    RoleModel getByName(String name) throws RoleNotFoundException;

    void ensureExists(long roleId) throws RoleNotFoundException;

}
