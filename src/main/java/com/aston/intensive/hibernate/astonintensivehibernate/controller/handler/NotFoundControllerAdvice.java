package com.aston.intensive.hibernate.astonintensivehibernate.controller.handler;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class NotFoundControllerAdvice {
    MessageSource messageSource;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception,
                                                                      Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(NOT_FOUND,
                requireNonNull(messageSource.getMessage("errors.404.title", new Object[0],
                        "errors.404.title", locale)));

        problemDetail.setProperty("errors", exception.getMessage());

        return ResponseEntity
                .status(NOT_FOUND)
                .body(problemDetail);
    }
}
