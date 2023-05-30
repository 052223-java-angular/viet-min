package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.Role;
import com.revature.app.models.User;
import com.revature.app.services.RoleService;
import com.revature.app.services.UserService;


public class UserServiceTest {
    @Mock
    private UserDAO useDAO;
    @Mock
    private RoleService roleService;
    private Role role;
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(useDAO, roleService);
    }
    
    //This test asserts that a user user123 is created with a role of user
    @Test
    public void testRegister() {
        String username = "user1234";
        String password = "pass1234";
        role = roleService.findByName("USER");

        User expected = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());
        User actual = userService.register(username, password);

        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getRoleId(), actual.getRoleId());
    }

    //This test asserts that an admin admin123 is created with a role of admin
    @Test
    public void testRegisterAdmin() {
        String username = "admin1234";
        String password = "pass1234";
        role = roleService.findByName("ADMIN");

        User expected = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());
        User actual = userService.register(username, password);

        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getRoleId(), actual.getRoleId());
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

        assertFalse(userService.isUniqueUserName(username));
    }

    //This test asserts that the method will return true if a username does not exist in database
    @Test
    public void test_IsUniqueUserName_True() {
        testRegister();

        String username = "user12345";

        assertFalse(userService.isUniqueUserName(username));
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
        String expected = "user1234";
        String password = "pass1234";

        User actual = userService.login(expected, password).get();
        //unable to check id because uuid
        assertEquals(expected, actual.getUsername());
    }

    //This test expects an empty optional object, credentials do not match
    @Test
    public void test_Login_False() {
        testRegister();
        String username = "user1234";
        String password = "pass12345";

        var expected = Optional.empty();
        User actual = userService.login(username, password).get();

        assertEquals(expected, actual);
    }

    //This test returns a user by username
    @Test
    public void test_FindByName() {
        String username = "user1234";
        role = roleService.findByName("USER");
        testRegister();

        //testing with uuid
        User actual = userService.findByName(username);
        assertEquals(username, actual.getUsername());
        assertEquals(role.getId(), actual.getRoleId());
    }

    //returns a user by id
    @Test
    public void test_FindByID() {
        testRegister();

        User expected = userService.findByName("user1234");
        String idString = expected.getId();

        User actual = userService.findById(idString);

        assertEquals(expected, actual);
    }
}
