package com.forum.areas.user.model.bind;

import java.io.Serializable;

public class UserLikeBindModel implements Serializable {

    private String username;

    public UserLikeBindModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}