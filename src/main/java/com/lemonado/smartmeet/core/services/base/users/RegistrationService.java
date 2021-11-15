package com.lemonado.smartmeet.core.services.base.users;

import com.lemonado.smartmeet.core.data.exceptions.*;
import com.lemonado.smartmeet.core.data.models.registration.RegistrationModel;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService {

    void startUserRegistration(String roleName, List<String> userEmails)
            throws RoleNotFoundException, ActionOnAdminRoleException, RegistrationProblemsException;

    void startUserRegistration(RoleModel roleModel, String userEmail)
            throws CanNotSendMailException, UserAlreadyRegisteredException;

    UserModel register(RegistrationModel registrationModel)
            throws UserAlreadyExistsException, CanNotCreateUserException, RegistrationCodeNotFoundException;


}
