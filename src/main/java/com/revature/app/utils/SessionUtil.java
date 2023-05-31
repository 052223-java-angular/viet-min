/**
 * A utility class that stores the current user session information and screen history.
 * It uses lombok annotations to generate constructors, getters, setters and toString methods.
 * It has fields for the user id, username, role id and a stack of screen paths.
 */
package com.revature.app.utils;

import java.util.Stack;

import com.revature.app.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionUtil {
    private String id;
    private String username;
    private String roleId;
    private Stack<String> screenHistory = new Stack<>();

    /**
     * Sets the session fields to the values of the given user object.
     * It assigns the user id, username and role id to the corresponding fields of this class. 
     * @param user The User object whose values are to be set as the session fields. 
     */
    public void setSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
    }
}