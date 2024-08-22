package com.supera.lista_tarefa.exception;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private String timestamp;

    public ErrorResponse(int status, String mensagem) {
        this.status = status;
        this.message = mensagem;
        this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss a z"));
    }
}
