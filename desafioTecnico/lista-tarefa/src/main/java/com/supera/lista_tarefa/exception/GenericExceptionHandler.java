package com.supera.lista_tarefa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RuntimeException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
