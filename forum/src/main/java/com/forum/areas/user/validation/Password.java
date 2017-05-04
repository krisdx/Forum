package com.forum.areas.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see PasswordValidator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default
            "Password must be at least 6 symbols long and has to contain a number and an upper case letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}