/**
 * A service class that provides business logic for roles.
 * It uses a RoleDAO object to perform CRUD operations on the roles table.
 */
package com.revature.app.services;

import java.util.Optional;

import com.revature.app.daos.RoleDAO;
import com.revature.app.models.Role;
import com.revature.app.utils.custom_exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
    private final RoleDAO roleDao;

    /**
     * Retrieves a role object that has a specific name from the roles table.
     * It uses the roleDao to find the role that has the same name as the parameter.
     * If no role is found, it throws a RoleNotFoundException.
     * @param name The name of the role to be retrieved.
     * @return A Role object that has the given name. 
     * @throws RoleNotFoundException If no role is found with the given name. 
     */
    public Role findByName(String name) throws RoleNotFoundException {
        Optional<Role> roleOpt = roleDao.findByName(name);

        return roleOpt.orElseThrow(RoleNotFoundException::new);
    }

}