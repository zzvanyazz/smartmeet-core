package com.lemonado.smartmeet.core.services.impl.users;

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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserModel login(String username, String password) throws LoginFailedException {
        var user = userModelRepository
                .findLive(username.toLowerCase())
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(password, user.passwordHash())) {
            throw new LoginFailedException();
        }
        return user;
    }

    @Override
    public UserModel findActiveUser(long userId) throws AuthenticationFailedException {
        return userModelRepository
                .findActive(userId)
                .orElseThrow(AuthenticationFailedException::new);
    }

    @Override
    public UserModel findActiveUser(String username) throws UserNotFoundException {
        return userModelRepository
                .findActive(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserModel> getUsers() {
        return userModelRepository.getAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userModelRepository.existsByEmail(email);
    }

    @Override
    public UserModel getUser(long userId) throws UserNotFoundException {
        return userModelRepository
                .getUser(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void assertExists(long id) throws UserNotFoundException {
        if (!userModelRepository.existsById(id))
            throw new UserNotFoundException();
    }

    @Override
    public UserModel createNewUser(UserModel newUserModel)
            throws UserAlreadyExistsException, CanNotCreateUserException {
        var username = newUserModel.username();
        if (userModelRepository.existsByName(username))
            throw new UserAlreadyExistsException(username);

        var user = userModelRepository.createUser(newUserModel);
        return user.orElseThrow(CanNotCreateUserException::new);
    }

}
