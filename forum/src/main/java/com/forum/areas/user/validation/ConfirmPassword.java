package com.forum.areas.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see ConfirmPasswordValidator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface ConfirmPassword {
    String message() default "Passwords Do Not Match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}