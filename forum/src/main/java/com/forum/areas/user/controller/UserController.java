package com.forum.areas.user.controller;

import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.user.exception.NoSuchUserException;
import com.forum.areas.user.model.view.UserViewModel;
import com.forum.areas.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String viewProfile(@PathVariable("username") String username, Model model) {
        UserViewModel userViewModel = this.userService.findByUsername(username);
        model.addAttribute("userViewModel", userViewModel);
        return "users/view-profile";
    }

    @ResponseBody
    @PutMapping("/edit/bio/{userId}")
    @PreAuthorize("hasRole('USER')")
    public void editBio(@PathVariable("userId") Long userId, EditBindModel bioEditBindModel) {
        this.userService.editBio(userId, bioEditBindModel);
    }

    @ExceptionHandler(NoSuchUserException.class)
    public String userNotFound(Model model) {
        model.addAttribute("title", "error.noSuchUser");
        return "error/error-template";
    }
}