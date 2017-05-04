package com.forum.areas.user.service;

import com.forum.areas.user.entity.Role;
import com.forum.areas.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String USER_ROLE = "USER_ROLE";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        Role userRole = this.roleRepository.getUserRole();
        if (userRole == null) {
            userRole = new Role();
            userRole.setAuthority(USER_ROLE);
            this.roleRepository.save(userRole);
        }

        return userRole;
    }
}