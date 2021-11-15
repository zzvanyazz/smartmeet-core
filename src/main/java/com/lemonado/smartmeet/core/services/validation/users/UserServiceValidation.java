package com.lemonado.smartmeet.core.services.validation.users;

import com.lemonado.smartmeet.core.data.exceptions.CanNotCreateUserException;
import com.lemonado.smartmeet.core.data.exceptions.LoginFailedException;
import com.lemonado.smartmeet.core.data.exceptions.UserAlreadyExistsException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import com.lemonado.smartmeet.core.repositories.UserModelRepository;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;

@Service
public class UserServiceValidation implements UserService {

    @Autowired
    private UserModelRepository userModelRepository;

    private final UserService userService;

    public UserServiceValidation(UserService userService) {
        this.userService = userService;
    }

    public UserModel login(String username, String password) throws LoginFailedException {
        return userService.login(username, password);
    }

    @Override
    public UserModel findActiveUser(long userId) throws AuthenticationFailedException {
        return userService.findActiveUser(userId);
    }

    @Override
    public UserModel findActiveUser(String username) throws UserNotFoundException {
        return userService.findActiveUser(username);
    }

    @Override
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }

    @Override
    public UserModel getUser(long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @Override
    public void assertExists(long id) throws UserNotFoundException {
        userService.assertExists(id);
    }

    @Override
    public UserModel createNewUser(UserModel newUserModel)
            throws UserAlreadyExistsException, CanNotCreateUserException {
        var username = newUserModel.username();
        if (userModelRepository.existsByName(username))
            throw new UserAlreadyExistsException(username);

        return userService.createNewUser(newUserModel);
    }

}
