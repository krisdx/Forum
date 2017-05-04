package com.forum.areas.question.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * A valid tag, can only contain:
 * <ul>
 *     <li>Lower Case Letters</li>
 *     <li>Digits</li>
 *     <li>_ (underscore)</li>
 * </ul>
 * <p>
 * The tag cannot start or finish with an underscore.
 * The length of the tag should be more than 1.
 * </p>
 * <p>The separated tags in the input must be separated with <strong>,</strong> (comma).</p>
 */
public class TagsValidator implements ConstraintValidator<Tags, String> {

    private static final int MIN_TAG_LENGTH = 1;
    private static final char UNDERSCORE = '_';

    @Override
    public void initialize(Tags constraint) {
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        Set<String> validTags = new HashSet<>();
        String[] tags = input.trim().split(",");
        for (String tag : tags) {
            if (!this.validTag(tag)) {
                return false;
            }

            if (validTags.contains(tag)){
                return false;
            }

            validTags.add(tag);
        }

        return true;
    }

    private boolean validTag(String tag) {
        if (tag.length() <= MIN_TAG_LENGTH) {
            return false;
        }

        char[] symbols = tag.trim().toCharArray();
        if (symbols[0] == UNDERSCORE || symbols[symbols.length - 1] == UNDERSCORE) {
            return false;
        }

        for (char ch : symbols) {
            boolean isLetterOrDigit = Character.isLetterOrDigit(ch);
            boolean isUpperCase = Character.isUpperCase(ch);
            boolean isUnderscore = ch == UNDERSCORE;

            if (isUpperCase) {
                return false;
            }

            if (!isLetterOrDigit && !isUnderscore) {
                return false;
            }
        }

        return true;
    }
}