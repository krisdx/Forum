package com.forum.controller;

import com.forum.areas.user.service.UserService;
import com.forum.config.LanguageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LanguageChangeController {

    private final UserService userService;

    @Autowired
    public LanguageChangeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/lang/{language}")
    public String changeLanguage(@PathVariable("language") String language, @RequestParam("uri") String currentUri) {
        if (language == null) {
            return "redirect:" + currentUri;
        }

        this.userService.changeLanguage(language);
        LanguageInterceptor.setLanguage(language);
        return "redirect:" + currentUri;
    }
}