package com.supera.lista_tarefa.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.supera.lista_tarefa.exception.ErrorResponse;
import com.supera.lista_tarefa.exception.GenericExceptionHandler;

class GenericExceptionHandlerTest {

    private GenericExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GenericExceptionHandler();
    }

    @Test
    void testHandleException_RuntimeException() {
        // Arrange
        String exceptionMessage = "An error occurred";
        RuntimeException runtimeException = new RuntimeException(exceptionMessage);

        // Act
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleException(runtimeException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertEquals(exceptionMessage, responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandleException_CustomMessage() {
        // Arrange
        String exceptionMessage = "Custom error message";
        RuntimeException runtimeException = new RuntimeException(exceptionMessage);

        // Act
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleException(runtimeException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertEquals(exceptionMessage, responseEntity.getBody().getMessage());
    }

}