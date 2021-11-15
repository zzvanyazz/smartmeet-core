package com.lemonado.smartmeet.core.services.validation.users;

import com.lemonado.smartmeet.core.data.exceptions.*;
import com.lemonado.smartmeet.core.data.models.registration.RegistrationModel;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import com.lemonado.smartmeet.core.services.base.users.RegistrationService;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceValidation implements RegistrationService {

    @Autowired
    private UserService userService;

    private final RegistrationService registrationService;

    public RegistrationServiceValidation(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void startUserRegistration(String roleName, List<String> userEmails)
            throws RoleNotFoundException, ActionOnAdminRoleException, RegistrationProblemsException {
        registrationService.startUserRegistration(roleName, userEmails);
    }

    @Override
    public void startUserRegistration(RoleModel roleModel, String userEmail)
            throws CanNotSendMailException, UserAlreadyRegisteredException {
        if (userService.existsByEmail(userEmail)) {
            throw new UserAlreadyRegisteredException();
        }
        registrationService.startUserRegistration(roleModel, userEmail);
    }

    @Override
    public UserModel register(RegistrationModel registrationModel)
            throws UserAlreadyExistsException, CanNotCreateUserException, RegistrationCodeNotFoundException {

        return registrationService.register(registrationModel);
    }


}
