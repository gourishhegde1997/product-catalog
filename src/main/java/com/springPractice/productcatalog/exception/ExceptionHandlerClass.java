package com.springPractice.productcatalog.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerClass {

    @Autowired
    private Environment env;

    @ExceptionHandler(ProductCatalogException.class)
    public ResponseEntity<ExceptionClass> exceptionHandler(ProductCatalogException exception) {
        ExceptionClass e = new ExceptionClass(HttpStatus.BAD_REQUEST.value(), env.getProperty(exception.getMessage()));
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionClass> invalidDtoHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        ExceptionClass e = new ExceptionClass(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionClass> invalidUriHandler(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        ExceptionClass e = new ExceptionClass(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
