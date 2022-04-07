package ru.trofimov.bikeroutes.exception;

import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RouteExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<EntityNotFoundException> getNonExistentEntity(HttpServletRequest request, NoSuchElementException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        EntityNotFoundException exception = new EntityNotFoundException(
                status,
                e.getMessage(),
                "No data with this id number",
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    ResponseEntity<EntityNotFoundException> invalidLoginOrPassword(HttpServletRequest request, InvalidRequestBodyException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        EntityNotFoundException exception = new EntityNotFoundException(
                status,
                e.getMessage(),
                "invalid login or password",
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }

    @ExceptionHandler(SignatureException.class)
    ResponseEntity<EntityNotFoundException> invalidToken(HttpServletRequest request, SignatureException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        EntityNotFoundException exception = new EntityNotFoundException(
                status,
                e.getMessage(),
                "invalid token",
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }
}
