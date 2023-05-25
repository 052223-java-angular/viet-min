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
    private String id;
    private String username;
    private String password;
    private String roleId;

    //Overloaded Constructor
    public User(String name, String pass, String roleId)
    {
        this.id = UUID.randomUUID().toString();
        this.username = name;
        this.password = pass;
        this.roleId = roleId;
    }
}