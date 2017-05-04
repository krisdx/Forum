package com.forum.areas.question.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see TagsValidator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TagsValidator.class)
public @interface Tags {
    String message() default
            "A tag can contain only lower case letters, digits and underscores and should have length more than 1." +
                    "The tag also cannot start or finish with an underscore." +
                    "The tags must be separated with commas (and cannot duplicate).";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}