package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mock.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.RoleDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.Role;
import com.revature.app.models.User;
import com.revature.app.services.RoleService;
import com.revature.app.services.UserService;

public class UserServiceTest {
    @Mock
    private UserDAO userDAO;
    @Mock
    private RoleService roleService;
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        
        userService = new UserService(userDAO, roleService);
    }
    
    //This test asserts that a user user123 is created with a role of user
    @Test
    public void testRegister() {
        final String username = "user1234";
        final String password = "pass1234";
        Role role = new Role("cc812463-1e4e-464b-9a56-f38d4711d0b0", "USER");

        User expected = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());
        when(roleService.findByName("USER")).thenReturn(role);
        doNothing().when(userDAO).save(any(User.class));

        User actual = userService.register(username, password);

        verify(userDAO, times(1)).save(any(User.class));
        
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    //This test asserts that the method will return true if username is between 8-20 characters
    @Test
    public void test_IsValidUserName_True() {
        String username = "user1234"; //8

        assertTrue(userService.isValidUserName(username));
    }

    //This test asserts that the method will return false if username is not between 8-20 characters
    @Test
    public void test_IsValidUserName_False() {
        String username = "user123"; //7
        String username2 = "user1234567891233294293455";

        assertFalse(userService.isValidUserName(username));
        assertFalse(userService.isValidUserName(username2));
    }

    //This test asserts that the method will return false if a username already exists in database
    @Test
    public void test_IsUniqueUserName_False() {
        testRegister();

        String username = "user1234";

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(new User()));

        assertFalse(userService.isUniqueUserName(username));
    }

    //This test asserts that the method will return true if a username does not exist in database
    @Test
    public void test_IsUniqueUserName_True() {
        testRegister();

        String username = "user12345";
        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());

        assertTrue(userService.isUniqueUserName(username));
    }

    //This test asserts that the method will return false if password is not between 8-20 characters with a letter and number
    @Test
    public void test_IsValidPassword_False() {
        String password = "12345678";
        String password2 = "www555";
        
        assertFalse(userService.isValidPassword(password));
        assertFalse(userService.isValidPassword(password2));
    }

    //This test asserts that the method will return true if password is between 8-20 characters with a letter and number
    @Test
    public void test_IsValidPassword_True() {
        String password = "12345678a";
        
        assertTrue(userService.isValidPassword(password));
    }

    //This test asserts that the method will return true if passwords are the same
    @Test
    public void test_IsSamePassword_True() {
        String password = "12345678a";
        String password2 = "12345678a";
        
        assertTrue(userService.isSamePassword(password, password2));
    }

    //This test asserts true if user logs in with correct credentials, it will return a user
    @Test
    public void test_Login_True() {
        testRegister();
        String username = "user1234";
        String password = "pass1234";
        Role role = new Role("cc812463-1e4e-464b-9a56-f38d4711d0b0", "USER");
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
        assertTrue(userService.login(username, password).isPresent());
    }

    //This test expects an empty optional object, credentials do not match
    @Test
    public void test_Login_False() {
        testRegister();
        String username = "user1234";
        String password = "pass12345";

        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());
        assertTrue(userService.login(username, password).isEmpty());
    }
    

    // //This test returns a user by username
    // @Test
    // public void test_FindByName() {
    //     String username = "user1234";
    //     testRegister();
    //     User user = new User();

    //     when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
    //     assertEquals(username, user.getUsername());
    //     //assertEquals(role.getId(), actual.getRoleId());
    // }

    // //returns a user by id
    // @Test
    // public void test_FindByID() {
    //     testRegister();

    //     User expected = userService.findByName("user1234");
    //     String idString = expected.getId();

    //     User actual = userService.findById(idString);

    //     assertEquals(expected, actual);
    // }
}
