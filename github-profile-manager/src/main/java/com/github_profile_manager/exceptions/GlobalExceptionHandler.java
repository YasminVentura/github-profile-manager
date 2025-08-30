package com.github_profile_manager.exceptions;

import com.github_profile_manager.exceptions.custom.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> validationErros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("Field '%s' %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Validation failed",
                HttpStatus.BAD_REQUEST.value(),
                validationErros
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Data conflict",
                HttpStatus.CONFLICT.value(),
                List.of("The provided data is a duplicate")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ObjectNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Object not found",
                HttpStatus.NOT_FOUND.value(),
                List.of(ex.getMessage())
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
