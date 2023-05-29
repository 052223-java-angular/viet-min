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

    public void setSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
    }
}