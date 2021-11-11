package com.lemonado.smartmeet.core.services.users;

import com.lemonado.smartmeet.core.data.exceptions.*;
import com.lemonado.smartmeet.core.data.models.registration.RegistrationModel;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import com.lemonado.smartmeet.core.data.models.users.builders.UserModelBuilder;
import com.lemonado.smartmeet.core.options.SecureOptions;
import com.lemonado.smartmeet.core.repositories.RegistrationRepository;
import com.lemonado.smartmeet.core.services.mail.MailService;
import com.lemonado.smartmeet.core.services.secure.SecureRandomService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private SecureOptions secureOptions;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private SecureRandomService secureRandomService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void startUserRegistration(String roleName, List<String> userEmails)
            throws RoleNotFoundException, ActionOnAdminRoleException, RegistrationProblemsException {
        var exceptions = new ArrayList<Exception>();

        var role = roleService.getByName(roleName);
        if (role.name().equals(RoleModel.ADMIN))
            throw new ActionOnAdminRoleException();
        for (String userEmail : userEmails) {
            try {
                startUserRegistration(role, userEmail);
            } catch (Exception e) {
                exceptions.add(e);
            }
        }
        if (!exceptions.isEmpty())
            throw new RegistrationProblemsException(exceptions);
    }

    public void startUserRegistration(RoleModel roleModel, String userEmail)
            throws CanNotSendMailException, UserAlreadyRegisteredException {
        if (userService.existsByEmail(userEmail)) {
            throw new UserAlreadyRegisteredException();
        }
        if (registrationRepository.existsByEmail(userEmail)) {
            registrationRepository.deleteByEmail(userEmail);
        }
        var registrationCode = generateRegistrationCode();
        mailService.sendRegistrationMessage(registrationCode, userEmail, roleModel);
        var roleId = roleModel.id();
        registrationRepository.save(userEmail, registrationCode, roleId);
    }

    private String generateRegistrationCode() {
        var length = secureOptions.getRegistrationCodeLength();
        try {
            return secureRandomService.generateAlphaNumeric(length);
        } catch (Exception e) {
            return RandomString.make(length);
        }
    }

    public UserModel register(RegistrationModel registrationModel)
            throws UserAlreadyExistsException, CanNotCreateUserException, RegistrationCodeNotFoundException {
        var reg = registrationRepository
                .getRegistrationModelByCode(registrationModel.code())
                .orElseThrow(RegistrationCodeNotFoundException::new);
        var userModel = new UserModelBuilder()
                .withUsername(registrationModel.username())
                .withEmail(reg.email())
                .withPasswordHash(passwordEncoder.encode(registrationModel.password()))
                .build();
        registrationRepository.deleteByEmail(reg.email());
        return userService.createNewUser(userModel);
    }


}
