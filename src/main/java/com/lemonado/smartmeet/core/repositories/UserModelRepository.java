package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.users.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserModelRepository {

    List<UserModel> getAll();

    Optional<UserModel> findLive(String email);

    Optional<UserModel> findActive(Long id);

    boolean existsById(long id);

    boolean existsByEmail(String email);

    Optional<UserModel> getUser(long userId);

    Optional<UserModel> createUser(UserModel userModel);

}
