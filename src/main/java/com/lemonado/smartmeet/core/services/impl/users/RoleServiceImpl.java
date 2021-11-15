package com.lemonado.smartmeet.core.services.impl.users;

import com.lemonado.smartmeet.core.data.exceptions.RoleNotFoundException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.repositories.RoleModelRepository;
import com.lemonado.smartmeet.core.services.base.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleModelRepository roleModelRepository;

    @Override
    public List<RoleModel> getAll() {
        return roleModelRepository.getAll();
    }

    @Override
    public long findId(String name) throws RoleNotFoundException {
        return roleModelRepository
                .findId(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }

    @Override
    public RoleModel getById(long id) throws RoleNotFoundException {
        return roleModelRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public RoleModel getByName(String name) throws RoleNotFoundException {
        return roleModelRepository
                .findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));

    }

    @Override
    public void ensureExists(long roleId) throws RoleNotFoundException {
        if (!roleModelRepository.isExists(roleId))
            throw new RoleNotFoundException();
    }

}
