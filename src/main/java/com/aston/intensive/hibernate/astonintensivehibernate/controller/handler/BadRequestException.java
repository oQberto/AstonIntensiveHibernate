package com.aston.intensive.hibernate.astonintensivehibernate.controller.handler;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BadRequestException {
    MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
                requireNonNull(messageSource.getMessage("errors.400.title", new Object[0],
                        "error.400.title", locale)));

        problemDetail.setProperty("errors",
                exception.getAllErrors()
                        .stream()
                        .map(ObjectError::getObjectName)
                        .toList());

        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }
}
