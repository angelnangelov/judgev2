package com.example.service;

import com.example.models.entities.Role;
import com.example.models.entities.enums.RoleEnum;

public interface RoleService {
    void initRoles();

    Role findRole(RoleEnum roleEnum);
}
