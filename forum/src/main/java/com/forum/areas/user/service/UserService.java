package com.forum.areas.user.service;

import com.forum.areas.question.entity.Question;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.model.view.UserViewModel;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {

    @PreAuthorize("hasRole('USER')")
    User addQuestionToCurrentUser(Question question);

    UserViewModel findByUsername(String username);

    @PreAuthorize("hasRole('USER')")
    void changeLanguage(String lang);

    String getCurrentLanguage();

    @PreAuthorize("hasRole('USER')")
    void editBio(Long userId, EditBindModel bioEditBindModel);
}