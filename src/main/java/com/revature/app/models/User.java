/**
 * A class that represents a user of an online store.
 * It has an id, a username, a password and a role id.
 */
package com.revature.app.models;

import java.util.UUID;

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
public class User {
    /**
     * The id of the user, generated randomly using UUID.
     */
    private String id;
    /**
     * The username of the user, used for logging in.
     */
    private String username;
    /**
     * The password of the user, used for logging in.
     */
    private String password;
    /**
     * The role id of the user, indicating the level of access and privileges.
     */
    private String roleId;


    /**
     * A constructor that takes the username, password and role id of the user and creates a new user with a random id.
     * @param username The username of the user, used for logging in.
     * @param password The password of the user, used for logging in.
     * @param roleId The role id of the user, indicating the level of access and privileges.
     */
    public User(String username, String password, String roleId) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}