package com.revature.app.services;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public User register(String username, String password){
        User newUser = new User(username, password, "cc812463-1e4e-464b-9a56-f38d4711d0b0");
        userDAO.save(newUser);
        return newUser;
    }
}
