package ru.trofimov.bikeroutes.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class EntityNotFoundException {
    private final HttpStatus status;
    private final String message;
    private final String detail;
    private final String instance;
    private final ZonedDateTime timestamp;

    public EntityNotFoundException(HttpStatus status, String message, String instance, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
        this.instance = instance;
        timestamp = ZonedDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
