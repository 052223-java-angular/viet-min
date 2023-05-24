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


    public User(String username, String password, String roleId) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}