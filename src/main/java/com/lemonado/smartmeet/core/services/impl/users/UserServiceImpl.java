package com.lemonado.smartmeet.core.services.impl.users;

import com.lemonado.smartmeet.core.data.exceptions.CanNotCreateUserException;
import com.lemonado.smartmeet.core.data.exceptions.LoginFailedException;
import com.lemonado.smartmeet.core.data.exceptions.UserNotFoundException;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import com.lemonado.smartmeet.core.repositories.UserRepository;
import com.lemonado.smartmeet.core.services.base.users.PasswordEncoder;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserModel login(String email, String password) throws LoginFailedException {
        var user = userRepository
                .findLive(email)
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(password, user.passwordHash())) {
            throw new LoginFailedException();
        }
        return user;
    }

    @Override
    public UserModel findActiveUser(long userId) throws AuthenticationFailedException {
        return userRepository
                .findActive(userId)
                .orElseThrow(AuthenticationFailedException::new);
    }

    @Override
    public UserModel findActiveUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findLive(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserModel> getUsers() {
        return userRepository.getAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserModel getUser(long userId) throws UserNotFoundException {
        return userRepository
                .getUser(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void assertExists(long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserModel createNewUser(UserModel newUserModel) throws CanNotCreateUserException {
        return userRepository.save(newUserModel);
    }

}
