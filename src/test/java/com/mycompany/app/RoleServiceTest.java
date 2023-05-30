package com.mycompany.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.RoleDAO;
import com.revature.app.models.Role;
import com.revature.app.services.RoleService;


public class RoleServiceTest {
    @Mock
    private RoleDAO roleDAO;
    @Mock
    private RoleService roleService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        roleService = new RoleService(roleDAO);
    }

    //Returns a role class by name
    @Test
    public void test_FindByName() {
        String roleName = "USER";
        String id = "cc812463-1e4e-464b-9a56-f38d4711d0b0";

        Role expected = new Role(id, roleName);
        Role actual = roleService.findByName(roleName);

        assertEquals(expected, actual);
    }
}
