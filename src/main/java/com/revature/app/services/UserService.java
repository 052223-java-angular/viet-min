package com.revature.app.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.Role;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final RoleService roleService;
    

    public User register(String username, String password){
        Role role = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, role.getId());
        userDAO.save(newUser);
        return newUser;
    }

    public User registerAdmin(String username, String password){
        Role role = roleService.findByName("ADMIN");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, role.getId());
        userDAO.save(newUser);
        return newUser;
    }
    
    public boolean isValidUserName(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUserName(String username){
        return userDAO.findByUsername(username).isEmpty();
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userDAO.findByUsername(username);

        if(user.isEmpty() || !BCrypt.checkpw(password, user.get().getPassword())){
            return Optional.empty();
        }
        return user;

    }

    public Optional<User> findByName(String username) {
        return null;
    }

    public Optional<User> findById(String user_id) {
        return null;
    }

}
