package com.example.service;

import com.example.models.entities.enums.RoleEnum;
import com.example.models.service.UserServiceModel;
import com.example.security.CurrentUser;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String > findAllUsernames();

    void changeRole(String username, RoleEnum roleEnum);
}
