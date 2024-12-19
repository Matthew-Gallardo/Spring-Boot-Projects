package org.matt.dev.codes.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
    @Autowired
    private MessageSource messageSource;

    private String getErrorMessage(String errorCode, Object... params) {
        String msg = "Something went wrong!";
        try {
            msg = messageSource.getMessage(errorCode, params, Locale.getDefault());
        } catch (Exception e) {
            log.error("Error fetching message for code: {}", errorCode, e);
        }
        return msg;
    }

    @ExceptionHandler(NoMessageException.class)
    public ResponseEntity<String> handleNoMessageException(NoMessageException ex) {
        String errorMessage = getErrorMessage("error.no_message", ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
