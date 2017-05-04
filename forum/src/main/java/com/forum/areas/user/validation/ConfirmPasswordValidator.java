package com.forum.areas.user.validation;

import com.forum.areas.user.model.bind.UserRegisterModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    @Override
    public void initialize(ConfirmPassword constraint) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (!(obj instanceof UserRegisterModel)) {
            String message = String.format("@%s applies only to %s", this.getClass().getSimpleName(), UserRegisterModel.class.getSimpleName());
            throw new IllegalArgumentException(message);
        }

        UserRegisterModel userRegisterModel = (UserRegisterModel) obj;
        return userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword());
    }
}
