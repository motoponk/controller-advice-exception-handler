package com.nibado.example.errorhandlers.example2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException ex) {
        log.error("User not found thrown", ex);
        return new ErrorResponse("USER_NOT_FOUND", "The user was not found");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured");
    }

    @Data
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
