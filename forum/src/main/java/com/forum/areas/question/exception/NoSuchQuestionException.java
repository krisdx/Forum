package com.forum.areas.question.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception will be thrown when the user tries to access a question, that does not exists.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The question does not exist.")
public class NoSuchQuestionException extends RuntimeException {

    private static final String MESSAGE = "The question does not exist";

    public NoSuchQuestionException() {
        super(MESSAGE);
    }
}