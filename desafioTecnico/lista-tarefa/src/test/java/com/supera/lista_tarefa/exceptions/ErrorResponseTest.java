package com.supera.lista_tarefa.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.supera.lista_tarefa.exception.ErrorResponse;

public class ErrorResponseTest {

    @Test
    void testErrorResponseConstructor() {
        // Dados de entrada para o construtor
        int status = 404;
        String message = "Not Found";
        
        // Cria a instância da classe ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse(status, message);
        
        // Verifica se o status foi atribuído corretamente
        assertEquals(status, errorResponse.getStatus());
        
        // Verifica se a mensagem foi atribuída corretamente
        assertEquals(message, errorResponse.getMessage());
        
        // Verifica se o timestamp não é nulo
        assertNotNull(errorResponse.getTimestamp());
    }
}