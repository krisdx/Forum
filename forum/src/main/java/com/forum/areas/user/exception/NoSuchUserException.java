package com.forum.areas.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class NoSuchUserException extends RuntimeException {

    private static final String MESSAGE = "User not found";

    public NoSuchUserException() {
        super(MESSAGE);
    }
}