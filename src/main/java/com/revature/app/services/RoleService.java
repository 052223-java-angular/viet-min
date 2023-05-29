package com.revature.app.services;

import java.util.Optional;

import com.revature.app.daos.RoleDAO;
import com.revature.app.models.Role;
import com.revature.app.utils.custom_exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
    private final RoleDAO roleDao;

    public Role findByName(String name) throws RoleNotFoundException {
        Optional<Role> roleOpt = roleDao.findByName(name);

        return roleOpt.orElseThrow(RoleNotFoundException::new);
    }

}
