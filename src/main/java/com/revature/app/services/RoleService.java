package com.revature.app.services;

import java.util.Optional;

import com.revature.app.daos.RoleDao;
import com.revature.app.models.Role;
import com.revature.app.utils.customException.RoleNotExist;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
    private final RoleDao roleDao;

    public Role findByName(String name) throws RoleNotExist {
        Optional<Role> roleOpt = roleDao.findByName(name);

        return roleOpt.orElseThrow(RoleNotExist::new);
    }

}
