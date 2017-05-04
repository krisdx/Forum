package com.forum.areas.user.service;

import com.forum.areas.user.model.bind.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginRegisterService extends UserDetailsService {
    void register(UserRegisterModel userRegisterModel);

    boolean hasUserWithUsername(String username);
}