package com.forum.areas.user.service;

import com.forum.areas.user.entity.Role;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.model.bind.UserRegisterModel;
import com.forum.areas.user.repository.UserRepository;
import com.forum.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    private static final String DEFAULT_LANGUAGE = Locale.ENGLISH.getLanguage();

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginRegisterServiceImpl(UserRepository userRepository,
                                    RoleService roleService,
                                    ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);
        user.setRegisterDate(LocalDate.now());

        String encryptedPassword = this.bCryptPasswordEncoder.encode(userRegisterModel.getPassword());
        user.setPassword(encryptedPassword);

        user.setLanguage(DEFAULT_LANGUAGE);

        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        Role userRole = this.roleService.getUserRole();
        user.getAuthorities().add(userRole);

        this.userRepository.save(user);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Constants.LOGIN_ERROR_MESSAGE);
        }

        return user;
    }
}