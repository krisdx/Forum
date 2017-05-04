package com.forum.areas.question.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception will be thrown when the user tries to access a category, that does not exists.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The category does not exist.")
public class NoSuchCategoryException extends RuntimeException {

    private static final String MESSAGE = "The category does not exist...";

    public NoSuchCategoryException() {
        super(MESSAGE);
    }
}