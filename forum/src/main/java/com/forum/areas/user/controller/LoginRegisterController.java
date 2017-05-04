package com.forum.areas.user.controller;

import com.forum.areas.user.model.bind.UserRegisterModel;
import com.forum.areas.user.service.LoginRegisterService;
import com.forum.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", Constants.LOGIN_ERROR_MESSAGE);
        }

        return "/login-register/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(@ModelAttribute UserRegisterModel userRegisterModel) {
        return "/login-register/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterModel userRegisterModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterModel);
            redirectAttributes.addFlashAttribute("bindResult", bindingResult);
            this.addPasswordMismatchAttribute(bindingResult, redirectAttributes);
            return "redirect:/register";
        }

        boolean hasUserWithUsername = this.loginRegisterService.hasUserWithUsername(userRegisterModel.getUsername());
        if (hasUserWithUsername) {
            String errorMessage = String.format("Username \"%s\" already exists.", userRegisterModel.getUsername());
            redirectAttributes.addFlashAttribute("duplicateUsername", errorMessage);
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterModel);
            redirectAttributes.addFlashAttribute("bindResult", bindingResult);
            return "redirect:/register";
        }

        this.loginRegisterService.register(userRegisterModel);
        return "redirect:/login";
    }

    private void addPasswordMismatchAttribute(BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error : errors) {
            if (error.getCode().equals(Constants.PASSWORD_MISMATCH_ERROR_CODE)) {
                redirectAttributes.addFlashAttribute("passwordMismatch", error.getDefaultMessage());
                return;
            }
        }
    }
}
