package com.lemonado.smartmeet.core.services.base.users;

import com.lemonado.smartmeet.core.data.exceptions.*;
import com.lemonado.smartmeet.core.data.models.users.UserModel;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;

@Service
public interface UserService {


    UserModel login(String email, String password) throws LoginFailedException;

    UserModel findActiveUser(long userId) throws AuthenticationFailedException;

    UserModel findActiveUserByEmail(String email) throws AuthenticationFailedException, UserNotFoundException;

    List<UserModel> getUsers();

    boolean existsByEmail(String email);

    UserModel getUser(long userId) throws UserNotFoundException;

    void assertExists(long id) throws UserNotFoundException;

    UserModel createNewUser(UserModel newUserModel)
            throws UserAlreadyExistsException, CanNotCreateUserException;

}
