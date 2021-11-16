package com.lemonado.smartmeet.core.repositories;

import com.lemonado.smartmeet.core.data.models.users.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserModelRepository {

    List<UserModel> getAll();

    Optional<UserModel> findLive(String name);

    Optional<UserModel> findActive(String name);

    Optional<UserModel> findActive(Long id);

    Optional<UserModel> findByName(String name);

    boolean existsByName(String name);

    boolean existsById(long id);

    boolean existsByEmail(String email);

    Optional<UserModel> getUser(long userId);

    Optional<UserModel> createUser(UserModel userModel);

}
