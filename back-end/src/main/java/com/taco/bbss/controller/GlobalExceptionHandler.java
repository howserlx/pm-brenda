package com.taco.bbss.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.taco.bbss.exception.PatientNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleIllegalStateException(HttpServletRequest req, Exception exception) {
        log.error("Unable to find patient or it doesn't belongs to the current user", exception);
    }


}
