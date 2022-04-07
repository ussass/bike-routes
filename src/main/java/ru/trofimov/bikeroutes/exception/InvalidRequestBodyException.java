package ru.trofimov.bikeroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidRequestBodyException extends RuntimeException {

    public InvalidRequestBodyException() {
        super();
    }

    public InvalidRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestBodyException(String message) {
        super(message);
    }

    public InvalidRequestBodyException(Throwable cause) {
        super(cause);
    }
}
