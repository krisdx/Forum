package com.forum.areas.question.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception will be thrown when the user tries to access a tag, that does not exists.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The tag does not exist.")
public class NoSuchTagException extends RuntimeException {

    private static final String MESSAGE = "The tag does not exist...";

    public NoSuchTagException() {
        super(MESSAGE);
    }
}