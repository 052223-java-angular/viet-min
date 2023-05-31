/**
 * A service class that provides business logic for users.
 * It uses a UserDAO object to perform CRUD operations on the users table.
 * It also uses a RoleService object to validate and retrieve role information.
 */
package com.revature.app.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.Role;
import com.revature.app.models.User;
import com.revature.app.utils.custom_exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final RoleService roleService;
    

    /**
     * Registers a new user with the given username and password and assigns them the USER role.
     * It hashes the password using BCrypt and creates a new User object with the USER role id.
     * It saves the new user using the userDAO and returns the user object.
     * @param username The username of the new user to be registered.
     * @param password The password of the new user to be registered.
     * @return A User object representing the new user with the USER role. 
     */
    public User register(String username, String password){
        Role role = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, role.getId());
        userDAO.save(newUser);
        return newUser;
    }

    /**
     * Registers a new user with the given username and password and assigns them the ADMIN role.
     * It hashes the password using BCrypt and creates a new User object with the ADMIN role id.
     * It saves the new user using the userDAO and returns the user object.
     * @param username The username of the new user to be registered.
     * @param password The password of the new user to be registered.
     * @return A User object representing the new user with the ADMIN role. 
     */
    public User registerAdmin(String username, String password){
        Role role = roleService.findByName("ADMIN");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, role.getId());
        userDAO.save(newUser);
        return newUser;
    }
    
    /**
     * Validates a given username using a regular expression for acceptable characters and length.
     * It checks if the username matches the pattern of 8 to 20 alphanumeric characters or underscores or dots,
     * but not starting or ending with an underscore or dot or having consecutive underscores or dots. 
     * @param username The username to be validated.
     * @return A boolean value indicating whether the username is valid or not. 
     */
    public boolean isValidUserName(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    /**
     * Checks if a given username is unique in the users table.
     * It uses the userDAO to find a user by the given username and returns true if no user is found. 
     * @param username The username to be checked for uniqueness.
     * @return A boolean value indicating whether the username is unique or not. 
     */
    public boolean isUniqueUserName(String username){
        return userDAO.findByUsername(username).isEmpty();
    }

    /**
     * Validates a given password using a regular expression for acceptable characters and length.
     * It checks if the password matches the pattern of at least 8 alphanumeric characters with at least one letter and one number. 
     * @param password The password to be validated.
     * @return A boolean value indicating whether the password is valid or not. 
     */
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    /**
     * Checks if two given passwords are equal or not.
     * It compares the two passwords using their equals method and returns true if they are equal. 
     * @param password The first password to be compared.
     * @param confirmPassword The second password to be compared.
     * @return A boolean value indicating whether the passwords are equal or not. 
     */
    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    /**
     * Logs in a user with the given username and password if they are valid and match an existing user in the users table.
     * It uses the userDAO to find a user by the given username and checks if the password matches the hashed password of the user using BCrypt.
     * If the user is found and the password is correct, it returns an optional user object. Otherwise, it returns an empty optional. 
     * @param username The username of the user to be logged in.
     * @param password The password of the user to be logged in.
     * @return An optional User object representing the logged in user if the credentials are valid. 
     */
    public Optional<User> login(String username, String password){
        Optional<User> user = userDAO.findByUsername(username);

        if(user.isEmpty() || !BCrypt.checkpw(password, user.get().getPassword())){
            return Optional.empty();
        }
        return user;

    }

    /**
     * Retrieves a user object that has a specific username from the users table.
     * It uses the userDAO to find the user that has the same username as the parameter.
     * If no user is found, it throws a UserNotFoundException.
     * @param username The username of the user to be retrieved.
     * @return A User object that has the given username. 
     * @throws UserNotFoundException If no user is found with the given username. 
     */
    public User findByName(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        return user.orElseThrow(UserNotFoundException::new);
    }

    /**
     * Retrieves a user object that has a specific id from the users table.
     * It uses the userDAO to find the user that has the same id as the parameter.
     * If no user is found, it throws a UserNotFoundException.
     * @param user_id The id of the user to be retrieved.
     * @return A User object that has the given id. 
     * @throws UserNotFoundException If no user is found with the given id. 
     */
    public User findById(String user_id) {
        Optional<User> user = userDAO.findById(user_id);
        return user.orElseThrow(UserNotFoundException::new);
    }

}