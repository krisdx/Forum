package com.forum.areas.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraint) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password.length() < 6) {
            return false;
        }

        Pattern numberPattern = Pattern.compile("");
        Matcher numberMatcher = numberPattern.matcher(password);
        Pattern upperCasePattern = Pattern.compile("");
        Matcher upperCaseMatcher = upperCasePattern.matcher(password);

        return numberMatcher.find() && upperCaseMatcher.find();
    }
}