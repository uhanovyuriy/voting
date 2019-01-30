package ru.myproject.voting.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ErrorToVoting extends RuntimeException {

    public ErrorToVoting(String message) {
        super(message);
    }
}
