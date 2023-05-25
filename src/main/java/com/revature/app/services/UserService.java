package com.revature.app.services;

import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.Role;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;
    private final RoleService roleService;

    //create a new account

    public User register(String username, String password) {
        Role existingRole = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, existingRole.getId());
        userDao.save(newUser);
        return newUser;
    }
}
