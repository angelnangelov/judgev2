package com.example.service.impl;

import com.example.models.entities.Role;
import com.example.models.entities.enums.RoleEnum;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(roleRepository.count()==0){
            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);

            Role user = new Role();
            user.setName(RoleEnum.USER);

            roleRepository.saveAndFlush(admin);
            roleRepository.saveAndFlush(user);
        }
    }

    @Override
    public Role findRole(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum).orElse(null);
    }
}
