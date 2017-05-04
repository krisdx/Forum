package com.forum.areas.user.model.bind;

import java.io.Serializable;

public class UserLoginModel implements Serializable {

    private String username;
    private String password;

    public UserLoginModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}