package com.example.service.impl;

import com.example.models.entities.User;
import com.example.models.entities.enums.RoleEnum;
import com.example.models.service.UserServiceModel;
import com.example.repository.UserRepository;
import com.example.security.CurrentUser;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setRole(roleService.findRole(RoleEnum.USER));
        userRepository.save(user);

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password)
                .map(user->modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
    currentUser.setId(userServiceModel.getId()).setUsername(userServiceModel.getUsername()).setRole(userServiceModel.getRole().getName());

    }

    @Override
    public void logout() {
        currentUser.
                setId(null)
                .setUsername(null)
                .setRole(null);
    }

    @Override
    public List<String> findAllUsernames() {
    return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleEnum roleEnum) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            if (user.get().getRole().getName() != roleEnum){
                user.get().setRole(roleService.findRole(roleEnum));
                userRepository.save(user.get());
            }
        }


    }
}
